#!/bin/sh

## We might want to load the kernel module
#modprobe at91_mci

## Create a new partition table with a single partition
## spanning the whole disk.
fdisk /dev/mmcblk0 << EOF
o
n
p
1


t
c
p
w
EOF

## Format the partition to VFAT
mkfs.vfat -n datafs -F 32 /dev/mmcblk0p1
