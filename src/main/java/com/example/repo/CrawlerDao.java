package com.example.repo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.google.common.collect.Queues.newArrayDeque;

@Service
@Scope("prototype")
public class CrawlerDao {

    private final Queue<String> urlQueue = newArrayDeque();

    private final Set<String> visitedUrls = new CopyOnWriteArraySet<>();

    public void appendToQueue(Collection<String> urls) {
        urlQueue.addAll(urls);
    }

    public void appendToQueue(String url) {
        urlQueue.add(url);
    }

    public String getNextUrl() {
        return urlQueue.poll();
    }

    public boolean hasUrlsToVisit() {
        return !urlQueue.isEmpty();
    }

    public boolean hasVisited(String uri) {
        return visitedUrls.contains(uri);
    }

    public void markAsVisited(String uri) {
        visitedUrls.add(uri);
    }

    public Set<String> getVisitedUrls(){
        return visitedUrls;
    }
}
