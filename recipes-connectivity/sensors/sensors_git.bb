SUMMARY = "Measure temperature, humidity, light intensity and soil moisture."
DESCRIPTION = "Measuring temperature, humidity, light intensity and soil moisture. \
Publishing the measurements from the sensors as MQTT messages."
SECTION = "console/network"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/Schmelzy/sensors.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"
PV = "1.0"

S = "${WORKDIR}/git"

inherit systemd

RDEPENDS:${PN} += " \
	python3-core \
	python3-spidev \
	python3-smbus \
	python3-paho-mqtt \
	rpi-gpio \
"

do_install() {
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		# Install systemd service to start the Python3 script
		install -d ${D}${systemd_unitdir}/system
		install -m 644 ${WORKDIR}/sensors.service ${D}${systemd_unitdir}/system
	fi

	install -d ${D}${bindir}
	install -m 0755 ${S}/sensors.py ${D}${bindir}/
	install -m 0644 ${S}/bh1750.py ${D}${bindir}/
        install -m 0644 ${S}/htu21d.py ${D}${bindir}/
        install -m 0644 ${S}/soil_moisture_sensor.py ${D}${bindir}/
}

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "sensors.service"

FILES:${PN} += "${bindir}/*"
