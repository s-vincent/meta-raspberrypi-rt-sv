FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.14.85"

SRCREV = "802d8776632344a4354d8ef5f142611a4c878570"

SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.14.y \
           http://xenomai.org/downloads/xenomai/stable/xenomai-3.0.8.tar.bz2;name=xeno \
           file://pre-ipipe-core-4.14.85-arm-6.patch;apply=0 \
           file://ipipe-core-4.14.85-arm-6.patch;apply=0 \
           file://post-ipipe-core-4.14.85-arm-6.patch;apply=0 \
           file://fragment.cfg \
           "

ARM_KEEP_OABI = "0"

require recipes-kernel/linux/linux-raspberrypi.inc

SRC_URI[xeno.md5sum] = "eafe3b789651f0db9575599dffc60a19"
SRC_URI[xeno.sha256sum] = "c373261ddb8280d9d7078cdd9cd9646dfb7d70d8cd3aa9693d9148f03990d711"

PV = "${LINUX_VERSION}"

# Fixes QA Error
FILES_kernel-base += "/lib/firmware"

# need that to make USB driver working with I-pipe kernel
# see https://raspberrypi.stackexchange.com/questions/4090/how-can-dwc-otg-speed-1-be-made-to-work
CMDLINE_prepend = 'dwc_otg.fiq_enable=0 dwc_otg.fiq_fsm_enable=0 dwc_otg.nak_holdoff=0 '

do_configure_prepend() {  
#  cp ${THISDIR}/${PN}/defconfig ${WORKDIR}/defconfig
}                         

do_prepare_kernel() {     
  xenomai_src="${WORKDIR}/xenomai-3.0.8/"

  echo "Apply pre-patch"
  cd ${S} && patch -p1 < ${WORKDIR}/pre-ipipe-core-4.14.85-arm-6.patch
  echo "Apply I-pipe patch"
  ${xenomai_src}/scripts/prepare-kernel.sh --arch=arm --linux=${S} --ipipe=${WORKDIR}/ipipe-core-4.14.85-arm-6.patch --default
  echo "Apply post-patch"
  cd ${S} && patch -p1 < ${WORKDIR}/post-ipipe-core-4.14.85-arm-6.patch
}                         

addtask prepare_kernel after do_patch before do_configure

