
https://activemq.apache.org/components/artemis/documentation/latest/docker.html#official-images

# Run on Docker

    docker pull apache/activemq-artemis

    docker run --detach --name activemq -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:latest-alpine

    Official Docker images are available on dockerhub. Images are pushed along with all the other distribution artifacts for every release. The fastest, simplest way to get started is with this command which will create and start a detached container named mycontainer, expose the main messaging port (i.e. 61616) and HTTP port (i.e. 8161), and remove it when it terminates:

    $ docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:latest-alpine
    Once the broker starts you can open the web management console at http://localhost:8161 and log in with the default username & password artemis.

    You can also use the shell command to interact with the running broker using the default username & password artemis, e.g.:

    $ docker exec -it mycontainer /var/lib/artemis-instance/bin/artemis shell --user artemis --password artemis


# Create alias commands:

    vi ~/.zshrc
    alias start-activemq='cd /Users/rame/Documents/Work/Apache/activemq/apache-activemq-5.17.0/bin/ && ./activemq start'
    alias stop-activemq='cd /Users/rame/Documents/Work/Apache/activemq/apache-activemq-5.17.0/bin/ && ./activemq stop'


    docker exec -it ubuntu bash
    vi ~/.bashrc

    
    
    
    alias start-zookeeper='cd /usr/src/kafka/confluent-7.5.1/bin/ && ./zookeeper-server-start /usr/src/kafka/confluent-7.5.1/etc/kafka/zookeeper.properties'
    alias start-broker='cd /usr/src/kafka/confluent-7.5.1/bin/ && ./kafka-server-start /usr/src/kafka/confluent-7.5.1/etc/kafka/server.properties'
    alias start-producer-cricket='cd /usr/src/kafka/confluent-7.5.1/bin/ && ./kafka-console-producer --broker-list localhost:9092 --topic cricket'
    alias start-consumer-cricket='cd /usr/src/kafka/confluent-7.5.1/bin/ && ./kafka-console-consumer --bootstrap-server localhost:9092 --topic cricket --from-beginning'

    alias start-producer-customer-events='cd /usr/src/kafka/confluent-7.5.1/bin/ && ./kafka-console-producer --broker-list localhost:9092 --topic customer-events'
    alias start-consumer-customer-events='cd /usr/src/kafka/confluent-7.5.1/bin/ && ./kafka-console-consumer --bootstrap-server localhost:9092 --topic customer-events --from-beginning'
    
    source ~/.bashrc

