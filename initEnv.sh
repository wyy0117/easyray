mvn -N install

cd ./easyray-framework-core
mvn -N install

cd easyray-framework-core-common && mvn -Dmaven.test.skip=true install && cd ..
cd easyray-framework-core-baseapi && mvn -Dmaven.test.skip=true install && cd ..
cd easyray-framework-core-api && mvn -Dmaven.test.skip=true install && cd ..
mvn -Dmaven.test.skip=true install

cd ../easyray-framework-component
componentPath=$(pwd)
find . -type d -name '*-api' -exec cd $componentPath/{} \; && mvn -Dmaven.test.skip=true install
cd $componentPath/..
