# This workspace belongs to Sitemap WebCrawler REST API Endpoint.

This application crawls the given domain recursively and generate the sitemap with list of images and external references.

## Stack
 1. Maven 3 (`brew install maven`)
 2. Java 8 ([download site](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))
 3. Spring Boot (part of maven build, no install necessary)

## Build Steps
 1. build and test `mvn clean install`(If this step fails, try `mvn clean install -DskipTests`.)
 2. run server `java -jar target/web-crawler-sitemap-0.0.1-SNAPSHOT.jar`
 3. Visit http://localhost:8080/api to see supported API. It has inbuilt REST client for testing the endpoints. Click on Try it Out! when done with ready with Request Params.

## Notes:
 1. This application is being built using Spring-Boot Java Stack(Inbuilt TomCat. No Installation needed).
 2. This application is being built with Testing approach. Integration tests were considered during development.
 3. Integration Tests deals with live data.
 4. REST Endpoints supports JSON response types.
 5. This is single threaded application, intended to crawl smaller to medium content based websites.

## Limitations:
1. This is single threaded application. It takes decent amount of time to crawl mid to high content websites.
2. Application crawls the website fairly. But it doesn't deal with the edge case of excessive scraping access denied.

## Future Improvements.
 1. Introduce WorkerTasks and ExecutorThread framework for better performance.
 2. Introduce Unit Tests.
 3. Introduce the persistance using DB.
 4. Work on edge cases.
 5. Introduce code coverage tool like Jacoco to maintain code coverage.
 6. Add Jmeter profile to src/test/jmeter.


## Supported Endpoint
Its recommended to test the endpoints using http://localhost:8080/api.
For REST Based clients.

`http://localhost:8080/api/docs/index.html`

POST: http://localhost:8080/api/crawl

`curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "{
  \"url\": \"www.atish.me\"
}" "http://localhost:8080/api/crawl"`


## Local dev setup
 1. Install [IntelliJ Ultimate](https://www.jetbrains.com/idea/download/). IT can provide a license.
 2. Install Lombok and Sprint Boot plugins (IntelliJ > Preferences > Plugins)
 3. Install checkstyle-idea plugins (IntelliJ > Preferences > Plugins)
 4. To run/debug the app from IDE, select CrawlerBootApplication class, right-click and run or debug.

## Steps
 1. Code Compile. `mvn clean compile`
 2. Run Unit Test `mvn clean test`
 3. Run Integration Test `mvn clean verify`
 4. Make Build `mvn clean install`

