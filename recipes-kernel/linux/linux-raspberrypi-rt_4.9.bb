FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.76"

SRCREV = "aae807a781807e95c761fe0a5babbc9bd5adaed6"
SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y \
           file://patch-4.9.76-rt61.patch \
           file://linux-rpi-4.9.y-clk.patch \
           file://fragment.cfg \
           "

require ${TOPDIR}/../meta-raspberrypi/recipes-kernel/linux/linux-raspberrypi.inc

