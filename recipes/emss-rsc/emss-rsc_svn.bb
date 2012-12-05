DESCRIPTION = "EMSS Receiver System Controller (RSC)"
LICENSE = "EMSS"
LIC_FILES_CHKSUM = "file://License;md5=8b5b6a5673fe685ee2425a573f0beb9f"

#SRCREV_pn-${PN} = "${AUTOREV}"
SRCREV = "440"

PV = "1.0+svnr${SRCPV}"
PR = "r1"

DEPENDS = "katcp"

inherit update-rc.d

INITSCRIPT_NAME = "rsc"
INITSCRIPT_PARAMS = "defaults 80 30"

SRC_URI = " \
	svn://tokyo.emss.co.za/repositories/Antennas/Projects/MK/Software;module=RSC;protocol=https \
	file://init \
	file://default \
	file://fix-makefile-for-qpc.patch;maxrev=440 \
"
S = "${WORKDIR}/RSC"

PARALLEL_MAKE = ""

do_configure() {
    :
}

do_compile() {
	oe_runmake -C qpc/ports/arm/linux/gnu all CONF=rel LIB=${AR}
	oe_runmake all CONF=rel
}

do_install() {
	install -D -m 0755 ${S}/rcmu ${D}${sbindir}/rsc
	install -D -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
	install -D -m 0644 ${WORKDIR}/default ${D}${sysconfdir}/default/${INITSCRIPT_NAME}
}

# List the config file so that opkg know about them
# and thus prevents over-writing modified files.
CONFFILES_${PN} = " \
	${sysconfdir}/default/${INITSCRIPT_NAME} \
"
