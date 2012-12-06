#!/bin/sh

if [ -f $1 ]; 
then
	flash_eraseall /dev/mtd4
	nandwrite -p /dev/mtd4 $1
else
	echo "No image selected"
	echo "Please include image in parameter 1"
fi
