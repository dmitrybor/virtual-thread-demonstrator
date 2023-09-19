#!/bin/bash

# install git
yum install git htop -y
#apt install git

mkdir vtd-test
cd vtd-test

# checkout vtd repo
git clone https://github.com/dmitrybor/virtual-thread-demonstrator.git
cd virtual-thread-demonstrator
export VTD_PROJECT_DIR=$(pwd)

# Install and launch service (comment/uncomment as required)
source ./ops-tools/install-delay-service.sh
# source ./ops-tools/prepare-for-gatling.sh
