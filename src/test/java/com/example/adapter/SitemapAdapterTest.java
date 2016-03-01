package com.example.adapter;

import com.example.CrawlerBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNull;

/**
 * Tests for DAOClientImpl.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CrawlerBootApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SitemapAdapterTest {

    /**
     * Test Sitemap Crawling.
     * @throws Exception the exception
     */
    @Test
    public void testSiteMapAdapterWithNullCheck() throws Exception {
        assertNull(SitemapBuilder.build(null, null, null, null));
    }
}
