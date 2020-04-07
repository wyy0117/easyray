mkdir -p ~/docker/data/zookeeper

echo 'dataDir=/data
dataLogDir=/datalog
clientPort=2181
'>~/docker/data/zookeeper/zoo.cfg
docker run --name zookeeper --restart always -itd -v ~/docker/data/zookeeper/data:/data -v ~/docker/data/zookeeper/datalog:/datalog -v ~/docker/data/zookeeper/logs:/logs -v ~/docker/data/zookeeper/zoo.cfg:/conf/zoo.cfg -e ZOO_LOG4J_PROP="INFO,ROLLINGFILE" -p 2181:2181 -p 2888:2888 -p 3888:3888 zookeeper
