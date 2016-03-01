package com.example.controller.rest;

import com.example.CrawlerBootApplication;
import com.example.model.UrlRequest;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for CrawlController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CrawlerBootApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CrawlControllerIT {
    /**
     * Web application Context for Unit Test
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * Mock MVC for Unit Testing
     */
    private MockMvc mvc;


    /**
     * Set up the tests.
     */
    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    /**
     * Test the CrawlService.
     */
    @Test
    public void testTheCrawlWebUrlWithSimpleWebsite() throws Exception {

        UrlRequest request = new UrlRequest();
        request.setUrl("www.newsweek.com");

        Gson gson = new Gson();
        String json = gson.toJson(request);

        mvc.perform(post(CrawlController.CRAWL).content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("www.newsweek.com")));
    }
}
