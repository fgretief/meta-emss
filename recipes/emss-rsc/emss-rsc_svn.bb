DESCRIPTION = "EMSS Receiver System Controller (RSC)"
LICENSE = "EMSS"
LIC_FILES_CHKSUM = "file://License;md5=8b5b6a5673fe685ee2425a573f0beb9f"

#SRCREV = "${AUTOREV}"
SRCREV = "1019"

PV = "1.0+svnr${SRCPV}"
PR = "r5"

DEPENDS = "katcp"

SRC_URI = " \
	svn://tokyo.emss.co.za/repositories/Antennas/Projects/MK/Software;module=RSC;protocol=https \
	file://fix-makefile-katcp-ini-xmodem.patch \
	file://disable-sdcard-mount.patch \
	file://profile \
	file://katcp-avahi.service \
	file://elapsed_time.sh \
	file://enable_debugger.sh \
	file://flash_kernel.sh \
	file://view_clock_speeds.sh \
"
S = "${WORKDIR}/RSC"

inherit systemd

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "rsc.service"
SRC_URI += "file://rsc.service"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " \
	--system \
	--shell /bin/sh \
	--no-create-home \
	--home /home/lib/rsc \
	--user-group rsc \
"

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
	# Add profile script to add sbin to PATH variable for rsc user
	install -D -m 0644 ${WORKDIR}/profile ${D}${sysconfdir}/profile.d/rsc
	# Add Avahi service description for discoverablity of katcp servers
	install -D -m 0644 ${WORKDIR}/katcp-avahi.service ${D}${sysconfdir}/avahi/services/katcp.service
	# Create home directory where we can store files
	install -d -m 0755 -o rsc -g rsc ${D}/home/rsc
	# Utility scripts, usefull for debugging and testing
	install -m 0644 ${WORKDIR}/elapsed_time.sh ${D}/home/rsc/elapsed_time.sh
	install -m 0644 ${WORKDIR}/enable_debugger.sh ${D}/home/rsc/enable_debugger.sh
	install -m 0644 ${WORKDIR}/flash_kernel.sh ${D}/home/rsc/flash_kernel.sh
	install -m 0644 ${WORKDIR}/view_clock_speeds.sh ${D}/home/rsc/view_clock_speeds.sh
}

FILES_${PN} += " \
	${sysconfdir}/profile.d \
	${sysconfdir}/avahi \
	/home/rsc \
"
