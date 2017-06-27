FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LINUX_VERSION ?= "4.1.21"

SRCREV := "ff45bc0e8917c77461b2901e2743e6339bb70413"
SRC_URI := "git://github.com/raspberrypi/linux.git;branch=rpi-4.1.y \
            file://linux-rpi-4.1.y-gcc-6.patch \
            file://002-xenomai-3-on-bcm-2709.patch \
            file://ipipe-core-4.1.18-arm-9.patch \
          "
SRC_URI += "https://git.xenomai.org/xenomai-3.git/snapshot/xenomai-3-3.0.5.tar.bz2"
SRC_URI[md5sum] = "00647d7b2c7e8b0fa17bba353214b466"
SRC_URI[sha256sum] = "2ddc8d1f85d593b4747ff31d4c5548e804bb9d2ce365a303b27012d1b7e37cd1"
SRC_URI += "file://defconfig"

require ${TOPDIR}/../meta-raspberrypi/recipes-kernel/linux/linux-raspberrypi.inc

KERNEL_DEVICETREE := " \                                                        
    bcm2708-rpi-b.dtb \                                                         
    bcm2708-rpi-b-plus.dtb \                                                    
    bcm2709-rpi-2-b.dtb \                                                       
    bcm2710-rpi-3-b.dtb \                                                       
    bcm2708-rpi-cm.dtb \                                                        
    overlays/hifiberry-amp-overlay.dtb \
    overlays/hifiberry-dac-overlay.dtb \
    overlays/hifiberry-dacplus-overlay.dtb \
    overlays/hifiberry-digi-overlay.dtb \
    overlays/i2c-rtc-overlay.dtb \
    overlays/iqaudio-dac-overlay.dtb \
    overlays/iqaudio-dacplus-overlay.dtb \
    overlays/lirc-rpi-overlay.dtb \
    overlays/pitft28-resistive-overlay.dtb \
    overlays/pps-gpio-overlay.dtb \
    overlays/rpi-ft5406-overlay.dtb \
    overlays/w1-gpio-overlay.dtb \
    overlays/w1-gpio-pullup-overlay.dtb \
    overlays/pi3-disable-bt-overlay.dtb \
    overlays/pi3-miniuart-bt-overlay.dtb \
    overlays/vc4-kms-v3d-overlay.dtb \
	"
# A LOADADDR is needed when building a uImage format kernel. This value is not
# set by default in rpi-4.8.y and later branches so we need to provide it
# manually. This value unused if KERNEL_IMAGETYPE is not uImage.
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"

do_configure_prepend() {
  cp ${THISDIR}/${PN}/defconfig ${WORKDIR}/defconfig
}

do_prepare_kernel() {
  linux_src="${S}"

  xenomai_src="${TMPDIR}/work/${MACHINE}-poky-${TARGET_OS}/${PN}/${EXTENDPE}${PV}-${PR}/xenomai-3-3.0.5/"

  $xenomai_src/scripts/prepare-kernel.sh --arch=arm --linux=$linux_src
}

addtask prepare_kernel after do_patch before do_configure

