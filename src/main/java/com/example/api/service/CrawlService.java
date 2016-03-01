package com.example.api.service;

import com.example.model.Sitemap;

import java.io.IOException;
import java.util.List;

public interface CrawlService {

    List<Sitemap> crawlTheWebUrl(String url) throws IOException;
}
