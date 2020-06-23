base_path=/Volumes/home/docker/data/mysql5.7
port=3306
container_name=mysql
root_password=root

mkdir -p $base_path/{conf,dbdata} &&
  echo "[mysql]
default-character-set=utf8

[mysqld]
character-set-server=utf8
lower_case_table_names=1

skip-host-cache
skip-name-resolve
default-time-zone = '+08:00'
max_allowed_packet=1024M
max_connections=1000" >~/docker/data/mysql5.7/conf/config-file.cnf &&
  docker run --restart=always -m 4g --name $container_name -p $port:3306 -v $base_path/conf:/etc/mysql/conf.d -v $base_path/dbdata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=$root_password -d mysql:5.7
