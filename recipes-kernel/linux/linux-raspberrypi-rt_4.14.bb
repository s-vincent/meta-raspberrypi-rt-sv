FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-rt:"

LINUX_VERSION ?= "4.14.34"
LINUX_RPI_DEV_BRANCH ?= "rpi-4.14.y-rt"

SRCREV = "32f5076d836518eaf2e7b2caa2e6ee196d27210b"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=${LINUX_RPI_DEV_BRANCH} \
           file://patch-4.14-bcm2835.patch \
           file://fragment.cfg \
					"

require recipes-kernel/linux/linux-raspberrypi.inc

# Fixes QA Error
FILES_kernel-base += "/lib/firmware"
#FILES_kernel-base += "/lib"

# Disable version check so that we don't have to edit this recipe every time
# upstream bumps the version
KERNEL_VERSION_SANITY_SKIP = "1"
