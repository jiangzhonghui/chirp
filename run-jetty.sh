#!/bin/bash

export CHIRP_DB_USR=root
export CHIRP_DB_PWD=password
export CHIRP_DB_HOST=localhost
export CHIRP_DB_PORT=3306
export CHIRP_DB_NAME=chirp
mvn clean install jetty:run -DskipTests=true
