package com.example.api.service;

import com.example.exception.UrlConnectionException;
import com.example.model.PageModel;

import java.io.IOException;

public interface PageScraper {

    PageModel getPageDetails(String url) throws UrlConnectionException, IOException;
}
