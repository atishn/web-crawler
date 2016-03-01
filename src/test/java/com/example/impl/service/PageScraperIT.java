package com.example.impl.service;

import com.example.CrawlerBootApplication;
import com.example.api.service.PageScraper;
import com.example.exception.UrlConnectionException;
import com.example.model.PageModel;
import org.apache.commons.collections4.CollectionUtils;
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

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertEquals;
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
public class PageScraperIT {

    /**
     * Expected exception.
     */
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * The Dao client.
     */
    @Autowired
    private PageScraper pageScraper;


    @Test
    public void getPageDetailTestSimplePage() throws Exception {
        PageModel model = pageScraper.getPageDetails("http://www.atish.me");

        assertNotNull(model);

        assertTrue(isNotEmpty(model.getTitle()));

        assertTrue(isEmpty(model.getReferences()));

        assertTrue(CollectionUtils.isNotEmpty(model.getExternalReferences()));
        assertEquals(model.getExternalReferences().size(), 9);

        assertTrue(CollectionUtils.isEmpty(model.getImages()));

        assertTrue(CollectionUtils.isNotEmpty(model.getStaticContents()));
        assertEquals(model.getStaticContents().size(), 3);
    }


    @Test
    public void getPageDetailTestMidContentPage() throws Exception {
        PageModel model = pageScraper.getPageDetails("http://www.wiprodigital.com");

        assertNotNull(model);

        assertTrue(isNotEmpty(model.getTitle()));

        assertTrue(CollectionUtils.isNotEmpty(model.getReferences()));
        assertTrue(model.getExternalReferences().size() > 0);

        assertTrue(CollectionUtils.isNotEmpty(model.getImages()));
        assertTrue(model.getImages().size() > 0);

        assertTrue(CollectionUtils.isNotEmpty(model.getExternalReferences()));
        assertTrue(model.getExternalReferences().size() > 100);

        assertTrue(CollectionUtils.isNotEmpty(model.getStaticContents()));
        assertTrue(model.getStaticContents().size() > 0);
    }


    @Test
    public void getPageDetailTestLargeContentPage() throws Exception {
        PageModel model = pageScraper.getPageDetails("http://www.newsweek.com");

        assertNotNull(model);

        assertTrue(isNotEmpty(model.getTitle()));

        assertTrue(CollectionUtils.isNotEmpty(model.getReferences()));
        assertTrue(model.getExternalReferences().size() > 0);

        assertTrue(CollectionUtils.isNotEmpty(model.getImages()));
        assertTrue(model.getImages().size() > 0);

        assertTrue(CollectionUtils.isNotEmpty(model.getExternalReferences()));
        assertTrue(model.getExternalReferences().size() > 0);

        assertTrue(CollectionUtils.isNotEmpty(model.getStaticContents()));
        assertTrue(model.getStaticContents().size() > 0);
    }

    @Test
    public void getPageDetailTestWithNull() throws Exception {
        assertNull(pageScraper.getPageDetails(null));
        assertNull(pageScraper.getPageDetails(""));
    }

    @Test
    public void getPageDetailsWithBadUrls() throws Exception {
        exception.expect(UrlConnectionException.class);
        pageScraper.getPageDetails("http://wiprxxxssDc----");
    }

    @Test
    public void testWithoutScheme() throws Exception {
        assertNotNull(pageScraper.getPageDetails("www.newsweek.com"));
        assertNotNull(pageScraper.getPageDetails("newsweek.com"));
    }

    @Test
    public void testRandomInsidePage() throws Exception {
        assertNotNull(pageScraper.getPageDetails("http://wiprodigital.com/tag/start-ups/"));
    }
}
