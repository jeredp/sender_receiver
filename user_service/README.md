docker pull rmohr/activemq
docker run -p 61616:61616 -p 8161:8161 rmohr/activemq

docker pull mdubc/mongodb-3.4
docker run -d -p 27017:27017 -p 28017:28017 -e AUTH=no dubc/mongodb-3.4