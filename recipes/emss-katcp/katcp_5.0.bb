DESCRPTION = "Katcp library"
HOMEPAGE = "http://github.com/ska-sa/katcp_devel"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r2"

SRC_URI = " \
    file://katcp-pruned-2012-11.tar.gz \
    file://01-makefile-install-fixes.patch \
"
S = "${WORKDIR}/katcp-pruned-2012-11"

do_compile_append() {
    oe_runmake -C katcp libkatcp.so
    (cd katcp; mv libkatcp.so libkatcp.so.5.0.0)
}

do_install() {
    oe_runmake PREFIX=${D}/usr install
    oe_libinstall -C katcp -a libkatcp ${D}${libdir}
}

FILES_${PN} += "${bindir}/* ${sbindir}/*"
