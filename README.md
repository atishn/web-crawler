# This workspace belongs to WebCrawler REST Endpoints.


## Stack
 1. Maven 3 (`brew install maven`)
 2. Java 8 ([download site](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))
 3. Spring Boot (part of maven build, no install necessary)

## Build Steps
 1. build and test `mvn clean install`
 2. run server `java -jar target/web-crawler-sitemap-0.0.1-SNAPSHOT.jar`
 3. Visit http://localhost:8080/api to see supported API. It has inbuilt REST client for testing the endpoints. Click on Try it Out! when done with ready with Request Params.

## Notes:
 1. This application is being built using Spring-Boot Java Stack(Inbuilt TomCat. No Installation needed).
 2. This application is being built with TDD approach. Unit & Integration tests were considered during Test Driven development.
 3. Intgeration Tests deals with live data base while Unit Tests deals with Mocks and Embedded DB.
 4. REST Endpoints supports JSON and XML content response types.


## Supported Endpoint
Its recommended to test the endpoints using http://localhost:8080/api.
For REST Based clients.

## Local dev setup
 1. Install [IntelliJ Ultimate](https://www.jetbrains.com/idea/download/). IT can provide a license.
 2. Install Lombok and Sprint Boot plugins (IntelliJ > Preferences > Plugins)
 3. Install checkstyle-idea plugins (IntelliJ > Preferences > Plugins)
 4. To run/debug the app from IDE, select CrawlerBootApplication class, right-click and run or debug.

## Steps and Scripts for Continuous Delivery(Jenkins) Pipeline.
 1. Code Compile. `mvn clean compile`
 2. Run Unit Test `mvn clean test`
 3. Run Integration Test `mvn clean verify`
 4. Make Build `mvn clean install`


## Future Improvements.
 1. Introduce code coverage tool like Jacoco to maintain code coverage.
 2. Add Jmeter profile to src/test/jmeter and configure the job in the pipeline.
