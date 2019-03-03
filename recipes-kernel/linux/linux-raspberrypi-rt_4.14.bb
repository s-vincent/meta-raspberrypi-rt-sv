FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-rt:"

LINUX_VERSION ?= "4.14.91"
LINUX_RPI_DEV_BRANCH ?= "rpi-4.14.y-rt"

SRCREV = "404fa4287aedbfba0daf3b1b792608405adf5f87"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=${LINUX_RPI_DEV_BRANCH} \
           file://fragment.cfg \
					"

require recipes-kernel/linux/linux-raspberrypi.inc

# Fixes QA Error
FILES_kernel-base += "/lib/firmware"
#FILES_kernel-base += "/lib"

# Disable version check so that we don't have to edit this recipe every time
# upstream bumps the version
KERNEL_VERSION_SANITY_SKIP = "1"
