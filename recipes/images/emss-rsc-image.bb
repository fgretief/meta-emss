LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r3"

require recipes-images/angstrom/console-base-image.bb

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_LINGUAS = ""
IMAGE_INSTALL += " \
    emss-rsc \
    katcp \
    ntpdate \
    ntp \
    ntp-bin \
    ntp-tickadj \
    tzdata-africa \
    rsync \
    mtd-utils \
    gdbserver \
    kernel-modules \
"

export IMAGE_BASENAME="rootfs-image"
