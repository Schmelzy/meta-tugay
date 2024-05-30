SRC_URI += "git://github.com/Schmelzy/sensors-user-interface.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"

do_install:append() {
	# Add HTML5 content from sensors-user-interface
	cp -r ${WORKDIR}/git/* ${D}${NGINX_WWWDIR}/html/
	chown ${NGINX_USER}:www-data -R ${D}${NGINX_WWWDIR}
}
