# Base this image on rpi-basic-image
include recipes-core/images/rpi-basic-image.bb

python () {                                                                     
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-ipipe":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-raspberrypi-ipipe to enable it")
}

DESCRIPTION = "A Raspberry Pi image with I-pipe/Xenomai kernel and real-time \
               test suite"
DEPENDS = "linux-raspberrypi-ipipe"

IMAGE_INSTALL += " \
                  linux-firmware-bcm43430 \
                  rt-tests \
                  hwlatdetect \
                  xenomai \
                  ntp \
                  wpa-supplicant \
                  iw \
                 "
IMAGE_FEATURES += "package-management"

