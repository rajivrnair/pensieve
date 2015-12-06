#!/usr/bin/env bash

echo "Cleaning, building and jar-ing"
./gradlew clean build shadowJar

echo "Applying db migrations..."
java -jar application/build/libs/pensieve-application.jar db migrate config/pensieve.yml

echo "Starting up..."
java -jar application/build/libs/pensieve-application.jar server config/pensieve.yml