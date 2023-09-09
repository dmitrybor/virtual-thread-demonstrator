#!/bin/bash

cd $VTD_PROJECT_DIR

# install jdk
source ./ops-tools/install-java.sh

# build service
./gradlew clean :delay-service:build -x :delay-service:test --no-daemon
./gradlew --stop

# install service
useradd -M vtd
usermod -L vtd
chmod +x delay-service/build/libs/delay-service-0.0.1-SNAPSHOT.jar
mkdir -p /opt/vtd/delay-service
cp -R delay-service/config /opt/vtd/delay-service/config
cp delay-service/build/libs/delay-service-0.0.1-SNAPSHOT.jar /opt/vtd/delay-service/delay-service.jar
cp ops-tools/service/delay.service /etc/systemd/system/delay.service

# start service
systemctl daemon-reload
systemctl start delay.service
systemctl enable delay.service
