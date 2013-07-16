DESCRIPTION = "Linux kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PR="r4"

KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "stamp9g20evb"

inherit kernel siteinfo

SRC_URI = "\
    ${KERNELORG_MIRROR}/linux/kernel/v3.0/linux-${PV}.tar.bz2 \
    file://defconfig \
"
SRC_URI_append_stamp9g20evb = " \
    file://at91-adc.110419.patch \
    file://ds1682.patch \
    file://emss-rcmu-board.patch \
"
S = "${WORKDIR}/linux-${PV}"

SRC_URI[md5sum] = "c943f3a1c5d6d926646f60f2408d65c5"
SRC_URI[sha256sum] = "17ba53298f7162ba9f1058b7798ad2fd3c086fb0e7cd7e0bcf02a69e26f71e21"
