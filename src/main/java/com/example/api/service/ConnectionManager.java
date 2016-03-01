package com.example.api.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * The interface Connection manager.
 */
public interface ConnectionManager {

    /**
     * Gets response stream.
     *
     * @param url the url
     *
     * @return the response stream
     *
     * @throws IOException the iO exception
     */
    InputStream getResponseStream(String url) throws IOException;
}
