SUMMARY = "A console-only image that fully supports the target device \
hardware and MQTT broker."

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL:append = " \
	nano vim \
	connman-client \
	i2c-tools \
	mosquitto mosquitto-clients \
	nginx \
	sensors \
"

EXTRA_IMAGE_FEATURES += "ssh-server-dropbear debug-tweaks tools-debug"
