FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.80"

SRCREV = "ffd7bf4085b09447e5db96edd74e524f118ca3fe"
SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y \
           file://patch-4.9.80-rpi-rt61.patch \
           file://linux-rpi-4.9.y-clk.patch \
           file://fragment.cfg \
           "

require recipes-kernel/linux/linux-raspberrypi.inc

