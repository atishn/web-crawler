/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.runner;

import com.example.util.UrlNormalizer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Queue;

/**
 */
public class WebCrawlerTask {

    // All URIs in a web page are discovered by searching for href-tags
    private static final String HREF_ATTR_SIGNATURE = "href=\"";
    private static final int HREF_ATTR_SIG_LENGTH = HREF_ATTR_SIGNATURE.length();

    private final URI startUri;
    private final Queue<String> uriQueue;
    private final HttpClient httpClient;

    public WebCrawlerTask(URI uri, Queue<String> uriQueue, HttpClient httpClient) {
        this.startUri = uri;
        this.httpClient = httpClient;
        this.uriQueue = uriQueue;
    }

    public void execute() {

        try {
            // Remove the URI from the task queue stored in DB
            // Don't crawl same page twice
            // Send an HTTP GET to the server and get the input stream for the content
            HttpResponse response = httpClient.execute(new HttpGet(startUri));
            InputStream in = response.getEntity().getContent();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                StringBuilder builder = new StringBuilder(4096);

                // Read the content and create a single string of it
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                String content = builder.toString();

                // The search position in content string
                int index = 0;
                // Search for 'href="' in the web page
                while ((index = content.indexOf(HREF_ATTR_SIGNATURE, index)) != -1) {
                    index += HREF_ATTR_SIG_LENGTH;
                    // Find the " that terminates the URI/href
                    int endOfHref = content.indexOf('\"', index);
                    // Extract the URI from the webpage
                    String subUri = content.substring(index, endOfHref);
                    // Try to parse/normalize it (tryNormalize returns null if it fails)
                    URI uri = UrlNormalizer.tryNormalize(startUri, subUri);
                    if (uri != null) {
                        uriQueue.add(uri.toASCIIString());
                    }
                    // Update the position to end of last href
                    index = endOfHref;
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
