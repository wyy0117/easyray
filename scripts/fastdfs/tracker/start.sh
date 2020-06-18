#!/usr/bin/env bash
base_path=/data/fastdfs/tracker1
container_name=fastdfs-tracker1

docker build . -t fastdfs-tracker

mkdir -p $base_path/{conf,dfs}
cp ./tracker.conf.sample $base_path/conf/tracker.conf

docker run -itd --restart=always --name $container_name --network=host -v $base_path/conf/tracker.conf:/etc/fdfs/tracker.conf -v $base_path/dfs:/home/dfs fastdfs-tracker
