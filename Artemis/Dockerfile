FROM openjdk:11-jdk-slim
WORKDIR /artemis
RUN apt update
RUN apt install wget -y
RUN wget -O artemis.tar.gz https://archive.apache.org/dist/activemq/activemq-artemis/2.17.0/apache-artemis-2.17.0-bin.tar.gz  
RUN tar -xzf artemis.tar.gz
RUN ["apache-artemis-2.17.0/bin/artemis", "create",  "mybroker", "--user", "jharry", "--password", "pass", "--allow-anonymous"]
COPY ./jolokia-access.xml mybroker/etc/jolokia-access.xml
ENTRYPOINT [ "mybroker/bin/artemis", "run"]