FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.68"

SRCREV = "d5a3b4cab0c8eacc93e58cfa1bdbd259399caedd"
SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y \
           file://patch-4.9.68-rt60.patch \
           file://linux-rpi-4.9.y-clk.patch \
           file://fragment.cfg \
           "

require ${TOPDIR}/../meta-raspberrypi/recipes-kernel/linux/linux-raspberrypi.inc

