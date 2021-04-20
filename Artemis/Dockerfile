FROM openjdk:11-jdk-slim
RUN apt update
RUN apt install wget -y
RUN wget -O artemis.tar.gz https://archive.apache.org/dist/activemq/activemq-artemis/2.17.0/apache-artemis-2.17.0-bin.tar.gz  
RUN tar -xzf artemis.tar.gz
RUN ["apache-artemis-2.17.0/bin/artemis", "create",  "mybroker", "--user", "jharry", "--password", "pass", "--allow-anonymous"]
ENTRYPOINT [ "mybroker/bin/artemis", "run"]
EXPOSE 8161
EXPOSE 61616