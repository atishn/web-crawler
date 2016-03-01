package com.example.configuration;

import com.example.controller.rest.CrawlController;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger configuration for API documentation.
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {

    /**
     * The Spring swagger config.
     */
    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Custom implementation.
     *
     * @return the swagger spring mvc plugin
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo()).includePatterns(swaggerPatterns());
    }

    /**
     * List of Swagger Patterns to be included.
     *
     * @return Array of Patterns.                                                                      ß
     */
    private String[] swaggerPatterns() {
        List<String> patterns = newArrayList();
        patterns.add(CrawlController.CRAWL + "?.*");

        return patterns.toArray(new String[patterns.size()]);
    }

    /**
     * Api info.
     *
     * @return the api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Web Crawler RESTFul API",
                "Crawling the Web and Beyond!",
                "http://newsweek.com",
                "me@atish.com",
                "Use Web Crawler Spring Boot App.",
                ""
        );
    }
}
