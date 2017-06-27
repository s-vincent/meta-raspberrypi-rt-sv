FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-rt:"

LINUX_VERSION ?= "4.11.6"

#SRCREV := "3c1164ccbadb34377d7cbf330b37dd903fef70de"
#SRCREV := "05afd4c0af6a43f6bda7caaacb01bc0116d50d3b"
#SRCREV := "be4248369e9122f07240d83f803bc7add54508c5"
SRCREV := "3b765d509be853022d5a7d9f4253c8a8f3bd27d3"

SRC_URI := "git://github.com/raspberrypi/linux.git;branch=rpi-4.11.y \
            file://patch-4.11.5-rt1.patch \
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
