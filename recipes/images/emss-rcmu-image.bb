LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

ALLOW_EMPTY = "1"

IMAGE_LINGUAS = ""
IMAGE_INSTALL += " \
    ntp \
"

export IMAGE_BASENAME="rcmu-image"

inherit core-image
