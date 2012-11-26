DESCRIPTION = "Linux kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PR="r1"

KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "stamp9g20evb"

inherit kernel siteinfo

SRC_URI = "\
    ${KERNELORG_MIRROR}/linux/kernel/v3.0/linux-${PV}.tar.bz2 \
    file://defconfig \
"
SRC_URI_append_stamp9g20evb = " \
    file://at91-adc.110419.patch \
"
S = "${WORKDIR}/linux-${PV}"

SRC_URI[md5sum] = "67ae0ca4e87fb22cecfce94cb90eaee9"
SRC_URI[sha256sum] = "7a6bee1fd68fd89bc78b26d22c5ecb8723340e787a403ddb47c98b6f6d7f6eec"
