DESCRIPTION = "Configuration files for online package repositories aka feeds"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PV = "${DISTRO_VERSION}"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FEED_URI ?= "http://192.168.1.26/feeds/${DISTRO_VERSION}/ipk/eglibc"

do_compile() {
	mkdir -p ${S}/${sysconfdir}/opkg
	echo "src/gz ${MACHINE_ARCH}  ${FEED_URI}/${MACHINE_ARCH}" > ${S}/${sysconfdir}/opkg/emss-feed.conf
	echo "src/gz armv5te  ${FEED_URI}/${FEED_ARCH}"           >> ${S}/${sysconfdir}/opkg/emss-feed.conf
	echo "src/gz all  ${FEED_URI}/all"                        >> ${S}/${sysconfdir}/opkg/emss-feed.conf
	echo "#src/gz sdk  ${FEED_URI}/sdk"                       >> ${S}/${sysconfdir}/opkg/emss-feed.conf
}

do_install () {
	install -d ${D}${sysconfdir}/opkg
	install -m 0644 ${S}/${sysconfdir}/opkg/emss-feed.conf ${D}${sysconfdir}/opkg/
}

FILES_${PN} = "${sysconfdir}/opkg/emss-feed.conf"
CONFFILES_${PN} += "${sysconfdir}/opkg/emss-feed.conf"

RRECOMMENDS_${PN} += "opkg"

# Get rid of opkg-collateral
RCONFLICTS_${PN} = "opkg-collateral"
RREPLACES_${PN} = "opkg-collateral"
RPROVIDES_${PN} = "opkg-collateral"
