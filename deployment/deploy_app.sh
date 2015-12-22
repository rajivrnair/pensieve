#!/bin/bash

echo "Stopping app container"
docker ps | grep pensieve-app | awk '{print $1}' | xargs docker stop

echo "Removing app container"
docker ps -a | grep pensieve-app | awk '{print $1}' | xargs docker rm

echo "Fetching latest image"
docker pull rajivrnair/pensieve

echo "Starting app"
docker-compose up -d pensieve-app
