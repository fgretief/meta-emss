DESCRIPTION = "EMSS data file system management"
AUTHOR = "Francois Retief <fgretief@gmail.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR="r1"

RDEPENDS = "util-linux-fdisk dosfstools"

SRC_URI = " \
    file://emss-create-datafs.sh \
    file://emss-datafs-creation.service \
    file://media-card.mount \
    "
S = "${WORKDIR}"

do_install() {
    install -m 0755 -d ${D}${sbindir}
    install -m 0755 ${WORKDIR}/emss-create-datafs.sh ${D}${sbindir}/

    install -m 0755 -d ${D}${systemd_unitdir}/system/local-fs.target.wants
    install -m 0644 ${WORKDIR}/media-card.mount ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/emss-datafs-creation.service ${D}${systemd_unitdir}/system/
    ln -s ../emss-datafs-creation.service ${D}${systemd_unitdir}/system/local-fs.target.wants/
    ln -s ../media-card.mount ${D}${systemd_unitdir}/system/local-fs.target.wants/
}

FILES_${PN} += " \
    ${sbindir}/emss-create-datafs.sh \
    ${systemd_unitdir}/system \
    "

inherit allarch systemd

SYSTEMD_PACKAGES = "${PN}"
#SYSTEMD_SERVICE_${PN} = "media-card.mount"
