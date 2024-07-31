# tournament-engine
This is the LBLCS api gate. Currently, it supports a single endpoint which we use to accept post-match callbacks from riot. Eventually, this will be the api each service queries to communicate data between each other. That is quite far away methinks...


### Running

This application runs through docker-compose. It requires a rabbitMQ server to be running, and specifies the rabbitmq host with HOST= in the docker-compose.yaml. If the containers are on the same docker network, this is just the container name.
