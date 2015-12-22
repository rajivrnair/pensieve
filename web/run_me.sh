#!/usr/bin/env bash

echo "Cleaning, building and jar-ing"
jenv exec ./gradlew clean build shadowJar

echo "Applying db migrations..."
jenv exec java -jar application/build/libs/pensieve-application.jar db migrate config/pensieve.yml

echo "Starting up..."
jenv exec java -jar application/build/libs/pensieve-application.jar server config/pensieve.yml