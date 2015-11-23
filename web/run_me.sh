#!/usr/bin/env bash

./gradlew clean shadowJar

java -jar build/libs/pensieve-all.jar server config/pensieve.yml