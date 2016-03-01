package com.example.api.service;

import com.example.exception.UrlConnectionException;
import com.example.model.PageModel;

import java.io.IOException;

/**
 * The interface Page scraper.
 */
public interface PageScraper {

    /**
     * Gets page details.
     *
     * @param url the url
     *
     * @return the page details
     *
     * @throws UrlConnectionException the url connection exception
     * @throws IOException            the iO exception
     */
    PageModel getPageDetails(String url) throws UrlConnectionException, IOException;
}
