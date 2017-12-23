# Base this image on core-image-minimal
include recipes-core/images/rpi-hwup-image.bb

python () {                                                                     
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-ipipe":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-raspberrypi-ipipe to enable it")
}

DESCRIPTION = "A Raspberry Pi image with I-pipe/Xenomai kernel and real-time \
               test suite"
DEPENDS = "linux-raspberrypi-ipipe"

IMAGE_INSTALL += " \
                  rt-tests \
                  hwlatdetect \
                  xenomai \
                  ntp \
                  linux-firmware-bcm43430 \
                 "
#IMAGE_FEATURES += "package-management"

