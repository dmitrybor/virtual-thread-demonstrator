#!/bin/bash

# install git
yum install git -y
#apt install git

mkdir vtd-test
cd vtd-test

# checkout vtd repo
git clone https://github.com/dmitrybor/virtual-thread-demonstrator.git
cd virtual-thread-demonstrator
git checkout feature/VTD-8-service-deployment
export VTD_PROJECT_DIR=$(pwd)

source ./ops-tools/install-delay-service.sh
