package com.example.api.service;

import com.example.model.Sitemap;

import java.io.IOException;

/**
 * The interface Crawl service.
 */
public interface CrawlService {

    /**
     * Crawl the web url.
     *
     * @param url the url
     *
     * @return the sitemap
     *
     * @throws IOException the iO exception
     */
    Sitemap crawlTheWebUrl(String url) throws IOException;
}
