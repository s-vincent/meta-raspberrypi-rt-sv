SUMMARY = "Xenomai userland binaries and libraries"
DESCRIPTION = "Xenomai userland binaries and libraries"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "xenomai"
PR = "r0"

SRC_URI = "https://git.xenomai.org/xenomai-3.git/snapshot/xenomai-3-3.0.5.tar.bz2 \
           file://xenomai-3.0.5-time.patch \
          "
SRC_URI[md5sum] = "00647d7b2c7e8b0fa17bba353214b466"
SRC_URI[sha256sum] = "2ddc8d1f85d593b4747ff31d4c5548e804bb9d2ce365a303b27012d1b7e37cd1"

S = "${WORKDIR}/xenomai-3-3.0.5"

inherit autotools pkgconfig

includedir = "/usr/include/xenomai"

FILES_${PN} += "/dev"
FILES_${PN} += "/dev/*"
FILES_${PN} += "/usr/bin/*"
FILES_${PN} += "/usr/sbin/*"
FILES_${PN} += "/usr/lib/*"
FILES_${PN} += "/usr/include/*"
FILES_${PN} += "/usr/share/doc/*"
FILES_${PN} += "/usr/share/man/*"
FILES_${PN} += "/usr/demo/*"

EXTRA_OECONF = " --enable-smp"
