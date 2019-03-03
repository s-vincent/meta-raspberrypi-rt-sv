FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.51"

SRCREV = "31f8c0c3f269675085e1271c9d3f76c42e672594"

SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y \
           file://linux-rpi-4.9.y-clk.patch \
           http://xenomai.org/downloads/xenomai/stable/xenomai-3.0.8.tar.bz2;name=xeno \
           file://ipipe-core-4.9.51-arm-3-rpi.patch;apply=0 \
           file://fragment.cfg \
           "

ARM_KEEP_OABI = "0"

require recipes-kernel/linux/linux-raspberrypi.inc

SRC_URI[xeno.md5sum] = "eafe3b789651f0db9575599dffc60a19"
SRC_URI[xeno.sha256sum] = "c373261ddb8280d9d7078cdd9cd9646dfb7d70d8cd3aa9693d9148f03990d711"

PV = "${LINUX_VERSION}"

# Fixes QA Error
FILES_kernel-base += "/lib/firmware"

do_configure_prepend() {  
#  cp ${THISDIR}/${PN}/defconfig ${WORKDIR}/defconfig
}                         

do_prepare_kernel() {     
  xenomai_src="${WORKDIR}/xenomai-3.0.8/"

  ${xenomai_src}/scripts/prepare-kernel.sh --arch=arm --linux=${S} --ipipe=${WORKDIR}/ipipe-core-4.9.51-arm-3-rpi.patch --default
}                         

addtask prepare_kernel after do_patch before do_configure

