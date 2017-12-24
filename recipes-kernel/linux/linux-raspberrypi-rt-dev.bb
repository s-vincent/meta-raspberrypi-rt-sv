python __anonymous() {
    if "linux-raspberrypi-rt-dev" not in d.getVar("PREFERRED_PROVIDER_virtual/kernel"):
        msg = "Skipping linux-raspberrypi-rt-dev as it is not the preferred " + \
              "provider of virtual/kernel."
        raise bb.parse.SkipRecipe(msg)
}

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-rt-dev:"

LINUX_VERSION ?= "4.14.7"
LINUX_RPI_DEV_BRANCH ?= "rpi-4.14.y"

SRCREV = "f55e87913ab71b00924b72bbff359cc2e0faaa7d"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=${LINUX_RPI_DEV_BRANCH} \
					 file://patch-4.14.6-rt7.patch \
           file://fragment.cfg \
					"

require ${TOPDIR}/../meta-raspberrypi/recipes-kernel/linux/linux-raspberrypi.inc

# Fixes QA Error
FILES_kernel-base += "/lib/firmware"
#FILES_kernel-base += "/lib"

# Disable version check so that we don't have to edit this recipe every time
# upstream bumps the version
KERNEL_VERSION_SANITY_SKIP = "1"
