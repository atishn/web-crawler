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

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for DAOClientImpl.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CrawlerBootApplication.class)
@WebAppConfiguration
@ActiveProfiles("integration")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CrawlServiceTest {

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
     * @throws Exception the exception
     */
    @Test
    public void testTheCrawlWebUrlWithAllSupportedProtocols() throws Exception {
        Sitemap sitemap = crawlService.crawlTheWebUrl("www.atish.me");
        assertNotNull(sitemap);

        assertTrue(isNotEmpty(sitemap.getStaticContents()));
        assertTrue(sitemap.getStaticContents().size() > 0);

        assertTrue(isNotEmpty(sitemap.getExternalReferences()));
        assertTrue(sitemap.getExternalReferences().size() > 0);


        sitemap = crawlService.crawlTheWebUrl("http://atish.me");
        assertNotNull(sitemap);

        assertTrue(isNotEmpty(sitemap.getStaticContents()));
        assertTrue(sitemap.getStaticContents().size() > 0);

        assertTrue(isNotEmpty(sitemap.getExternalReferences()));
        assertTrue(sitemap.getExternalReferences().size() > 0);

        sitemap = crawlService.crawlTheWebUrl("http://www.atish.me");
        assertNotNull(sitemap);

        assertTrue(isNotEmpty(sitemap.getStaticContents()));
        assertTrue(sitemap.getStaticContents().size() > 0);

        assertTrue(isNotEmpty(sitemap.getExternalReferences()));
        assertTrue(sitemap.getExternalReferences().size() > 0);

        sitemap = crawlService.crawlTheWebUrl("atish.me");
        assertNotNull(sitemap);

        assertTrue(isNotEmpty(sitemap.getStaticContents()));
        assertTrue(sitemap.getStaticContents().size() > 0);

        assertTrue(isNotEmpty(sitemap.getExternalReferences()));
        assertTrue(sitemap.getExternalReferences().size() > 0);
    }

    /**
     * Test the crawl web mid content url.
     *
     * @throws Exception the exception
     */
    @Test
    public void testTheCrawlWebMidContentUrl() throws Exception {
        Sitemap sitemap = crawlService.crawlTheWebUrl("http://wiprodigital.com");
        assertNotNull(sitemap);

        assertTrue(isNotEmpty(sitemap.getUrl()));
        assertTrue(sitemap.getUrl().size() > 10);

        assertTrue(isNotEmpty(sitemap.getImages()));
        assertTrue(sitemap.getImages().size() > 10);

        assertTrue(isNotEmpty(sitemap.getStaticContents()));
        assertTrue(sitemap.getStaticContents().size() > 10);

        assertTrue(isNotEmpty(sitemap.getExternalReferences()));
        assertTrue(sitemap.getExternalReferences().size() > 10);


    }

    /**
     * Test the crawltest web mid page url.
     *
     * @throws Exception the exception
     */
    @Test
    public void testTheCrawltestWebMidPageUrl() throws Exception {
        Sitemap sitemap = crawlService.crawlTheWebUrl("http://wiprodigital.com/blog/");
        assertNotNull(sitemap);

        assertTrue(isNotEmpty(sitemap.getUrl()));
        assertTrue(sitemap.getUrl().size() > 0);

        assertTrue(isNotEmpty(sitemap.getImages()));
        assertTrue(sitemap.getImages().size() > 0);

        assertTrue(isNotEmpty(sitemap.getStaticContents()));
        assertTrue(sitemap.getStaticContents().size() > 0);

        assertTrue(isNotEmpty(sitemap.getExternalReferences()));
        assertTrue(sitemap.getExternalReferences().size() > 0);
    }


    /**
     * Test the crawltest bad cases.
     *
     * @throws Exception the exception
     */
    @Test
    public void testTheCrawltestBadCases() throws Exception {
        Sitemap sitemap = crawlService.crawlTheWebUrl(null);
        assertNull(sitemap);

        sitemap = crawlService.crawlTheWebUrl("");
        assertNull(sitemap);
    }
}
