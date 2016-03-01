package com.example.impl.service;

import com.example.api.service.CrawlService;
import com.example.api.service.PageScraper;
import com.example.exception.UrlConnectionException;
import com.example.model.PageModel;
import com.example.model.Sitemap;
import com.example.repo.CrawlerDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.example.adapter.SitemapBuilder.build;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * CrawlService implementation.
 */
@Service
public class CrawlServiceImpl implements CrawlService {

    /**
     * The constant LOG.
     */
    private static final Logger LOG = getLogger(PageScraperImpl.class);
    /**
     * Dao client.
     */
    @Autowired
    private PageScraper pageScraper;

    @Autowired
    private CrawlerDao crawlerDao;


    @Override
    public Sitemap crawlTheWebUrl(String url) throws IOException {

        Sitemap sitemap = null;

        if (isNotBlank(url)) {
            crawlerDao.appendToQueue(url);

            Set<String> imageReferences = new CopyOnWriteArraySet<>();
            Set<String> externalRefernces = new CopyOnWriteArraySet<>();
            Set<String> staticContents = new CopyOnWriteArraySet<>();

            while (crawlerDao.hasUrlsToVisit()) {
                String urlToVisit = crawlerDao.getNextUrl();
                if (crawlerDao.hasVisited(urlToVisit)) continue;
                try {
                    PageModel pageDetail = pageScraper.getPageDetails(urlToVisit);
                    crawlerDao.appendToQueue(pageDetail.getReferences());
                    if (isNotEmpty(pageDetail.getImages())) {
                        imageReferences.addAll(pageDetail.getImages());
                    }
                    if (isNotEmpty(pageDetail.getStaticContents())) {
                        staticContents.addAll(pageDetail.getStaticContents());
                    }
                    if (isNotEmpty(pageDetail.getExternalReferences())) {
                        externalRefernces.addAll(pageDetail.getExternalReferences());
                    }
                } catch (UrlConnectionException ex) {
                    LOG.error("The given url cannot be fetched " + urlToVisit);
                }
                crawlerDao.markAsVisited(urlToVisit);
            }
            sitemap = build(crawlerDao.getVisitedUrls(), imageReferences, staticContents, externalRefernces);
        }
        return sitemap;
    }
}
