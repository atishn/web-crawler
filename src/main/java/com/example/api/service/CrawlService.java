package com.example.api.service;

import com.example.model.Sitemap;

import java.util.List;

public interface CrawlService {

    List<Sitemap> crawlTheWebUrl(String url) throws InterruptedException;
}
