#!/usr/bin/env bash

docker exec -it mysql mysql -uroot -proot -e "CREATE DATABASE IF NOT EXISTS easyray DEFAULT CHARSET utf8 COLLATE utf8_general_ci;"
