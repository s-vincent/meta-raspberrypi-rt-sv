FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION ?= "4.9.36"

SRCREV := "c0e97d579b9efca6e554dfdfcaf930c58b79c77f"

SRC_URI := "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y \
            file://patch-4.9.35-rt25.patch \
            file://linux-rpi-4.9.y-clk.patch \
          "
SRC_URI += "file://defconfig"

require ${TOPDIR}/../meta-raspberrypi/recipes-kernel/linux/linux-raspberrypi.inc

# A LOADADDR is needed when building a uImage format kernel. This value is not
# set by default in rpi-4.8.y and later branches so we need to provide it
# manually. This value unused if KERNEL_IMAGETYPE is not uImage.
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"

do_configure_prepend() {
  cp ${THISDIR}/${PN}/defconfig ${WORKDIR}/defconfig
}
