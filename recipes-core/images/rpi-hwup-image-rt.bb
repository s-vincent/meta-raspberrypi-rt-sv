# Base this image on core-image-minimal
include recipes-core/images/rpi-hwup-image.bb

python () {                                                                     
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-raspberrypi-rt to enable it")
}

DESCRIPTION = "A Raspberry Pi image with PREEMPT-RT kernel and real-time test \
               suite"
DEPENDS = "linux-raspberrypi-rt"

IMAGE_INSTALL += " \
                  rt-tests \
                  hwlatdetect \
                  ntp \
                  linux-firmware-bcm43430 \
                 "
#IMAGE_FEATURES += "package-management"

