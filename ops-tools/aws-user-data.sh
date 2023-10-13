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
#---------------------------------------------------------

# Install Delay Service
#source ./ops-tools/install-delay-service.sh

#---------------------------------------------------------

# Install Sync Service with virtual threads configuration
#export THREAD_TYPE='virtual'
#export SERVICE_PORT=8001
#export DELAY_SERVICE_URL=http://172.31.31.25:8000
#source ./ops-tools/install-sync-service.sh

#---------------------------------------------------------

# Install Sync Service with platform threads configuration
#export THREAD_TYPE='platform'
#export SERVICE_PORT=8001
#export DELAY_SERVICE_URL=http://172.31.31.25:8000
#source ./ops-tools/install-sync-service.sh

#---------------------------------------------------------

# Configure the system to run Gatling
#source ./ops-tools/prepare-for-gatling.sh

#---------------------------------------------------------
