# Java development assignment

Create a Spring Boot application that polls a RSS feed every 5 minutes, and stores any changes in a in-memory database like h2.

The news feed to use is: http://feeds.nos.nl/nosjournaal?format=xml. Create a data model to store at least the last 10 news items in the database. For each item at least the title, description, publication date and image needs to be stored. It could be an item is updated in the feed, in which case its record in the database should be updated as well.

Expose the news items using GraphQL, see https://github.com/graphql-java-kickstart/graphql-spring-boot. Also include graphiql-spring-boot-starter for exposing the GraphiQL IDE to interact with the GraphQL API.

Deliver a (small) readme file along with the source code which describes how to set up and run the application.

Preferably the source code is shared using an online repository such as github or bitbucket.

# Implementation

## Structure

1. `configuration` Contains Jackson object mapper configuration and the configuration property for the feed URL.
2. `core` Contains the business logic and domain entities and the two use cases used to store and retrieve the stored data.
3. `entrypoints` Contains the scheduled job and GraphQL api definition.
4. `dataproviders.`
   1. `dataproviders.database` Contains the database entity and repository.
   2. `dataproviders.rest` Contains the data provider for the rss feed.

## How to run the application

First, import the project in your IDE.

Run the `main` function contained in the `NewsFeedAssignmentApplication.java` file.
Once the application is running, access the playground via the `http://localhost:8080/playground` URL.

Optionally, a different news feed can be configured in the application.yml file.

## How to run the tests

* Option 1: run all JUnit tests found under `src/test/java` in your IDE.
* Option 2: run the Maven test build lifecycle action by launching the `mvn test` command.
