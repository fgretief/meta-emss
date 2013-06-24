LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r5"

CONMANPKGS = ""

require recipes-images/angstrom/console-base-image.bb

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_LINGUAS = ""
IMAGE_INSTALL += " \
    emss-rsc \
    emss-rsc-systemd \
    emss-datafs \
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
    dosfstools \
    connman \
    connman-systemd \
    connman-plugin-loopback \
    connman-plugin-ethernet \
"

export IMAGE_BASENAME="rootfs-image"

do_extra_actions() {
    ## Create file to silence pam_env warning.
    install -m 0755 -d ${IMAGE_ROOTFS}${sysconfdir}/default
    touch ${IMAGE_ROOTFS}${sysconfdir}/default/locale
    ## Change default.target to multi-user.target because we don't
    ## have a graphical environment.
    ln -f -s multi-user.target '${IMAGE_ROOTFS}${systemd_unitdir}/system/default.target'
}

ROOTFS_POSTPROCESS_COMMAND += "do_extra_actions ; "
