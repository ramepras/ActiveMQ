# Download ActiveMQ
  apache-activemq-5.17.0

# Create alias commands:

    vi ~/.zshrc
    alias start-activemq='cd /Users/rame/Documents/Work/Apache/activemq/apache-activemq-5.17.0/bin/ && ./activemq start'
    alias stop-activemq='cd /Users/rame/Documents/Work/Apache/activemq/apache-activemq-5.17.0/bin/ && ./activemq stop'

# Start ActiveMQ from terminal: start-activemq

Main: Then start the application: java -jar spring-activemq-app.jar
   It exposes endpoints publish and consume endpoints.
   It is running on 9090

Consumer: Then start consumer app: java -jar spring-activemq-consume-app.jar
   It consumes message from Main application (every 5 sec)
   It is running on 9091

Publisher: Then start publish app: java -jar spring-activemq-publish-app.jar
   It publishes message to Main application (every 5 sec)
   It is running on 9092


# To publish message manually.
   curl -X POST \
   http://localhost:9090/publish \
   -H 'Content-Type: application/json' \
   -d '{
   "message": "Hello ActiveMQ!"
   }'

# To consume message manually
   curl -X POST \
   http://localhost:9090/consume

