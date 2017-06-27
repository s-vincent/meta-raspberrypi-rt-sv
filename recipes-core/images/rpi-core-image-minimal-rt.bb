# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

python () {                                                                     
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-rt" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-rt-dev":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-raspberrypi-rt to enable it")
}

DESCRIPTION = "A Raspberry Pi image with PREEMPT-RT kernel and real-time test \
               suite"
#DEPENDS = "linux-raspberrypi-rt"

IMAGE_INSTALL += " \
                  rt-tests \
                  hwlatdetect \
                 "
#IMAGE_FEATURES += "package-management"
