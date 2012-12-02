DESCRIPTION = "Linux kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PR="r3"

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

SRC_URI[md5sum] = "0bb97ed099986b46dc513c3178e891b3"
SRC_URI[sha256sum] = "b9a9dd61c111d96e6899bcff80d9bf1568acd5165a04f134f9c01f237eed2287"
