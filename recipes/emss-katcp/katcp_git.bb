DESCRPTION = "Katcp library"
HOMEPAGE = "http://github.com/ska-sa/katcp_devel"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "c47b0e06071ca91f8d1a1846d35cf5f3a6d15f05"
PV = "1.0+git${SRCPV}"
PR = "r1"

SRC_URI = " \
    git://github.com/ska-sa/katcp_devel.git;protocol=git \
    file://01-makefile-install-fixes.patch \
"
S = "${WORKDIR}/git"

do_compile_append() {
    oe_runmake -C katcp libkatcp.so
    (cd katcp; mv libkatcp.so libkatcp.so.0.0.0)
}

do_install() {
    oe_runmake PREFIX=${D}/usr install
    oe_libinstall -C katcp -a libkatcp ${D}${libdir}
    rm -rf ${D}${libdir}/kcs
}

FILES_${PN} += "${bindir}/* ${sbindir}/*"
