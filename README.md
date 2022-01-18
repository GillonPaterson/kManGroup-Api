# kManGroup-Api 

## Project Description 

This is the API for the kManGroup which is an example of an API functionally it works but practically as a project that is useful not so much. Although this is the backend to the project that contains a Mysql database and a java Api.

In this project we used java17

## How to start the WebService application

1. Run `mvn clean install` to build your application.
2. Start application by going to the webServiceApplication and run it. 
3. To check that your application is running go to 'http://localhost:8080/swagger' that will allow you to send requests to your endpoints 

### Testing

java testing is kept in the tests folder and holds service and validator tests. 

## Health Check

To see your applications health enter URL `http://localhost:8081/healthcheck`

## WorkFlows

All the config files for the CI pipeline are held in the .github/workflows and the config files for the linters are held in the .github/linters.

### workFlows we run

1. superlinter -documentation-  https://github.com/github/super-linter
2. pa11y -documentation- https://github.com/pa11y/pa11y

### Projects Other Repos

1. Frontend - https://github.com/GillonPaterson/kManGroupApp
2. Performance Testing - https://github.com/GillonPaterson/gatling-tests - needs to be run with java11

### Rules

1. You break it you fix it
2. If Gillon did it and it doesn't work check his spelling
3. Ignore dependabot at all costs

### Contributing

we aren't open to contributing sorry
