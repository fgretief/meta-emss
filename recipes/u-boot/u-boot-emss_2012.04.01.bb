require recipes-bsp/u-boot/u-boot.inc

PR = "r1"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

COMPATIBLE_MACHINE = "(stamp9g20evb)"

S = "${WORKDIR}/u-boot-${PV}"

SRC_URI = "ftp://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2"

SRC_URI_append_stamp9g20evb = " \
    file://001-taskit-uboot-v4.patch \
"

SRC_URI[md5sum] = "192bb231082d9159fb6e16de3039b6b2"
SRC_URI[sha256sum] = "db44041d39d9c31567babc3be85143b6acff45ff6f3693abf7e973bdc3dd95b0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
