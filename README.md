# tournament-engine
The internal engine driving the LBLCS tournaments, which handles tiebreakers and calculates standings as series progress.


### Running

This application runs through docker-compose. It requires a rabbitMQ server to be running, and specifies the rabbitmq host with HOST= in a .env file.