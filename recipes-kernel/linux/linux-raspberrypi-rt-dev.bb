FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-rt:"

LINUX_VERSION ?= "4.11.9"

SRCREV := "9ce082ec24f6ace78335e0a6225b7541033b79ce"

SRC_URI := "git://github.com/raspberrypi/linux.git;branch=rpi-4.11.y \
            file://patch-4.11.9-rt7.patch \
            file://linux-rpi-4.11.y-clk.patch \
          "
SRC_URI += "file://defconfig"

require ${TOPDIR}/../meta-raspberrypi/recipes-kernel/linux/linux-raspberrypi.inc

# A LOADADDR is needed when building a uImage format kernel. This value is not
# set by default in rpi-4.8.y and later branches so we need to provide it
# manually. This value unused if KERNEL_IMAGETYPE is not uImage.
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"

do_configure_prepend() {
  cp ${THISDIR}/linux-raspberrypi-rt/defconfig ${WORKDIR}/defconfig
}
