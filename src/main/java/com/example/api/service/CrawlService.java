package com.example.api.service;

import com.example.model.Sitemap;

import java.io.IOException;

public interface CrawlService {

    Sitemap crawlTheWebUrl(String url) throws IOException;
}
