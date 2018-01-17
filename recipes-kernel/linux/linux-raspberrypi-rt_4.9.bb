FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.77"

SRCREV = "783daf505c2d8ecf26eeb4b4cd657f4b48072540"
SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y \
           file://patch-4.9.76-rt61.patch \
           file://linux-rpi-4.9.y-clk.patch \
           file://fragment.cfg \
           "

require ${TOPDIR}/../meta-raspberrypi/recipes-kernel/linux/linux-raspberrypi.inc

