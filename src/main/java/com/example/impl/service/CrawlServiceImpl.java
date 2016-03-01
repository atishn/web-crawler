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
import java.util.List;

import static com.example.adapter.SitemapAdapter.adapt;
import static com.google.common.collect.Lists.newArrayList;
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
    public List<Sitemap> crawlTheWebUrl(String url) throws IOException{

        List<Sitemap> sitemaps = newArrayList();
        crawlerDao.appendToQueue(url);

        while (crawlerDao.hasUrlsToVisit()) {
            String urlToVisit = crawlerDao.getNextUrl();
            if (crawlerDao.hasVisited(urlToVisit)) continue;
            try {
                PageModel pageDetail = pageScraper.getPageDetails(urlToVisit);
                Sitemap sitemap = adapt(pageDetail);
                sitemaps.add(sitemap);
                crawlerDao.appendToQueue(pageDetail.getReferences());
            } catch (UrlConnectionException ex) {
                LOG.error("The given url cannot be fetched " + urlToVisit);
            }
            crawlerDao.markAsVisited(urlToVisit);
        }
        return sitemaps;
    }
}
