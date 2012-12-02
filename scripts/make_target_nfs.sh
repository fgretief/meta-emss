#!/bin/sh -e

# Script to unpack an image into a folder that is shared via NFS to our
# boards, allowing remote execution without having to program NAND flash.

MACHINE=stamp9g20evb
OEBASE=${HOME}/v

TARGET_PATH=targetNFS
TARGET_IMAGE=${MACHINE}/rootfs-image-${MACHINE}.tar.gz

[ -d "${OEBASE}" ] || (echo "Unable to find OE base directory"; exit 1)
[ -f "${OEBASE}/oe-deploy/images/${TARGET_IMAGE}" ] || (echo "Unable to find image"; exit 1)

sudo echo "Unpacking to ${OEBASE}/${TARGET_PATH}"

# create a backup of the current folder
[ -d ${OEBASE}/${TARGET_PATH}.old ] && sudo rm -rf ${OEBASE}/${TARGET_PATH}.old
[ -d ${OEBASE}/${TARGET_PATH} ] && sudo mv ${OEBASE}/${TARGET_PATH} ${OEBASE}/${TARGET_PATH}.old

# create the new folder
sudo mkdir -p ${OEBASE}/${TARGET_PATH}

# unpack the tarball into that folder
if [ -d ${OEBASE}/${TARGET_PATH} ]; then
    cd ${OEBASE}/${TARGET_PATH}/

    sudo tar -xvzf "${OEBASE}/oe-deploy/images/${TARGET_IMAGE}"

    sudo chmod 777 home/root boot lib/modules
    sudo chmod 777 lib/modules/3.0.53

    #sudo mv boot/uImage-3.0.53 boot/uImage-3.0.53x
    #sudo mv lib/modules/3.0.53 lib/modules/3.0.53x
fi
