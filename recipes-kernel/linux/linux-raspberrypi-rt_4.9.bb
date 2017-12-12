FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.65"

SRCREV = "46cd72d4d8879b2ac9ae634fd891defa1f1fa83f"
SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y \
           file://patch-4.9.65-rt56.patch \
           file://linux-rpi-4.9.y-clk.patch \
           file://fragment.cfg \
           "

require ${TOPDIR}/../meta-raspberrypi/recipes-kernel/linux/linux-raspberrypi.inc

