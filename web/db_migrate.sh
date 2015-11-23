#!/usr/bin/env bash

java -jar build/libs/pensieve-all.jar db status config/pensieve.yml

java -jar build/libs/pensieve-all.jar db migrate config/pensieve.yml