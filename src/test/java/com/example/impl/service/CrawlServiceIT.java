package com.example.impl.service;

import com.example.CrawlerBootApplication;
import com.example.api.service.CrawlService;
import com.example.model.Sitemap;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

/**
 * Tests for DAOClientImpl.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CrawlerBootApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CrawlServiceIT {

    /**
     * Expected exception.
     */
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * The Dao client.
     */
    @Autowired
    private CrawlService crawlService;

    /**
     * Test the CrawlService.
     */
    @Test
    public void testTheCrawlWebUrl() throws Exception {
        List<Sitemap> sitemapList = crawlService.crawlTheWebUrl("http://wiprodigital.com");
        assertTrue(isNotEmpty(sitemapList));
    }
}
