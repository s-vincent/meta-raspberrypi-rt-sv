# Base this image on core-image-minimal
include recipes-core/images/rpi-hwup-image.bb

python () {                                                                     
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-rt" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-rt-dev":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-raspberrypi-rt to enable it")
}

DESCRIPTION = "A Raspberry Pi image with PREEMPT-RT kernel and real-time test \
               suite"
DEPENDS = "(linux-raspberrypi-rt|linux-raspberrypi-rt-dev)"

IMAGE_INSTALL += " \
                  rt-tests \
                  hwlatdetect \
                  ntp \
                  linux-firmware-bcm43430 \
                 "
#IMAGE_FEATURES += "package-management"

