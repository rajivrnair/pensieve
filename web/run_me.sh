#!/usr/bin/env bash

echo "Cleaning, building and jar-ing"
./gradlew clean shadowJar

echo "Applying db migrations..."
java -jar build/libs/pensieve-all.jar db migrate config/pensieve.yml

echo "Starting up..."
java -jar build/libs/pensieve-all.jar server config/pensieve.yml