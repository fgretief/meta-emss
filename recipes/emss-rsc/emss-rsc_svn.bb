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
"
S = "${WORKDIR}/RSC"

do_configure() {
    :
}

do_install() {
	install -d ${D}/home/rsc/main
	install -D -m 0755 ${S}/rcmu ${D}/home/rsc/main/RSC

	install -D -m 0755 ${S}/rcmu ${D}${sbindir}/rsc
	install -D -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
	install -D -m 0644 ${WORKDIR}/default ${D}${sysconfdir}/default/${INITSCRIPT_NAME}
}

# List the config file so that opkg know about them
# and thus prevents over-writing modified files.
CONFFILES_${PN} = " \
	${sysconfdir}/default/${INITSCRIPT_NAME} \
"
