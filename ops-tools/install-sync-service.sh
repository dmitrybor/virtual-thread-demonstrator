#!/bin/bash

cd $VTD_PROJECT_DIR

# install jdk
source ./ops-tools/install-java.sh
if [ $1 = 'virtual' ]
then
  export THREAD_TYPE='virtual'
else
  export THREAD_TYPE='platform'
fi

export SERVICE_NAME="$THREAD_TYPE"sync
export SERVICE_PORT="$2"
export DELAY_SERVICE_URL="$3"

echo "Deploying $SERVICE_NAME service"
export SERVICE_WORKING_DIRECTORY="/opt/vtd/$THREAD_TYPE-sync-service"
mkdir -p $SERVICE_WORKING_DIRECTORY

# build service
./gradlew clean :sync-service:build -x :sync-service:test --no-daemon
./gradlew --stop

# install service
useradd -M vtd
usermod -L vtd
chmod +x sync-service/build/libs/sync-service-0.0.1-SNAPSHOT.jar
cp sync-service/build/libs/sync-service-0.0.1-SNAPSHOT.jar "$SERVICE_WORKING_DIRECTORY/$SERVICE_NAME-service.jar"
mkdir "$SERVICE_WORKING_DIRECTORY/config"
cat sync-service/config/template-for-application.properties | envsubst > "$SERVICE_WORKING_DIRECTORY/config/application.properties"
cat ops-tools/service/template-for-sync.service | envsubst > "/etc/systemd/system/$SERVICE_NAME.service"

# start service
systemctl daemon-reload
systemctl start "$SERVICE_NAME.service"
systemctl enable "$SERVICE_NAME.service"
