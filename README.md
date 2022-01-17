# WebService

## Project Description 

This is the API for the kManGroup which is an example of an API functionally it works but practically as a project that is useful not so much. 

## How to start the WebService application

1. Run `mvn clean install` to build your application.
2. Start application by going to the webServiceApplication and run it. 
3. To check that your application is running go to 'http://localhost:8080/swagger' that will allow you to send requests to your endpoints 

##Testing

java testing is kept in the tests folder and holds service and validator tests. 

## Health Check

To see your applications health enter URL `http://localhost:8081/healthcheck`

## WorkFlows

All the config files for the CI pipeline are held in the .github/workflows and the config files for the linters are held in the .github/linters.

### workFlows we run

1. superlinter
2. pa11y 

### Rules

1. You break it you fix it
2. If Gillon did it and it doesn't work check his spelling

### Contributing

we aren't open to contributing sorry
