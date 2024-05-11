1) To publish message 
curl -X POST \
   http://localhost:8080/publish \
   -H 'Content-Type: application/json' \
   -d '{
   "message": "Hello ActiveMQ!"
   }'


2) To consume message
   curl -X POST \
   http://localhost:8080/consume
