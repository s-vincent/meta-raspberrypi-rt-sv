# Base this image on rpi-basic-image
include recipes-core/images/rpi-basic-image.bb

KERNEL_DEVICETREE := " \
    bcm2708-rpi-b.dtb \
    bcm2708-rpi-b-plus.dtb \
    bcm2709-rpi-2-b.dtb \
    bcm2710-rpi-3-b.dtb \
    bcm2708-rpi-cm.dtb \
    overlays/hifiberry-amp-overlay.dtb \
    overlays/hifiberry-dac-overlay.dtb \
    overlays/hifiberry-dacplus-overlay.dtb \
    overlays/hifiberry-digi-overlay.dtb \
    overlays/i2c-rtc-overlay.dtb \
    overlays/iqaudio-dac-overlay.dtb \
    overlays/iqaudio-dacplus-overlay.dtb \
    overlays/lirc-rpi-overlay.dtb \
    overlays/pitft28-resistive-overlay.dtb \
    overlays/pps-gpio-overlay.dtb \
    overlays/rpi-ft5406-overlay.dtb \
    overlays/w1-gpio-overlay.dtb \
    overlays/w1-gpio-pullup-overlay.dtb \
    overlays/pi3-disable-bt-overlay.dtb \
    overlays/pi3-miniuart-bt-overlay.dtb \
    overlays/vc4-kms-v3d-overlay.dtb \
    "

python () {                                                                     
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-ipipe":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-raspberrypi-ipipe to enable it")
}

DESCRIPTION = "A Raspberry Pi image with I-pipe kernel and Xenomai suite."
DEPENDS = "linux-raspberrypi-ipipe"

IMAGE_INSTALL += " \
                  rt-tests \
                  xenomai \
                 "
IMAGE_FEATURES += "package-management"

