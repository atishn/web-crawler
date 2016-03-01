package com.example.repo;

import java.util.Collection;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.google.common.collect.Queues.newArrayDeque;

/**
 * The type Crawler dao.
 */
public class CrawlerDao {

    /**
     * The Url queue.
     */
    private final Queue<String> urlQueue = newArrayDeque();

    /**
     * The Visited urls.
     */
    private final Set<String> visitedUrls = new CopyOnWriteArraySet<>();

    /**
     * Get new instance.
     *
     * @return the crawler dao
     */
    public static CrawlerDao getNewInstance() {
        return new CrawlerDao();
    }

    /**
     * Append to queue.
     *
     * @param urls the urls
     */
    public void appendToQueue(final Collection<String> urls) {
        urlQueue.addAll(urls);
    }

    /**
     * Append to queue.
     *
     * @param url the url
     */
    public void appendToQueue(final String url) {
        urlQueue.add(url);
    }

    /**
     * Gets next url.
     *
     * @return the next url
     */
    public String getNextUrl() {
        return urlQueue.poll();
    }

    /**
     * Has urls to visit.
     *
     * @return the boolean
     */
    public boolean hasUrlsToVisit() {
        return !urlQueue.isEmpty();
    }

    /**
     * Has visited.
     *
     * @param uri the uri
     *
     * @return the boolean
     */
    public boolean hasVisited(final String uri) {
        return visitedUrls.contains(uri);
    }

    /**
     * Mark as visited.
     *
     * @param uri the uri
     */
    public void markAsVisited(final String uri) {
        visitedUrls.add(uri);
    }

    /**
     * Get visited urls.
     *
     * @return the set
     */
    public Set<String> getVisitedUrls() {
        return visitedUrls;
    }
}
