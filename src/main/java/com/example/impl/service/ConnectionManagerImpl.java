package com.example.impl.service;

import com.example.api.service.ConnectionManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * CrawlService implementation.
 */
@Service
public class ConnectionManagerImpl implements ConnectionManager {

    /**
     * The constant LOG.
     */
    private static final Logger LOG = getLogger(ConnectionManagerImpl.class);

    PoolingHttpClientConnectionManager cm;

    CloseableHttpClient httpClient;

    @Override
    public InputStream getResponseStream(String url) throws IOException {

        HttpResponse response = getHttpClient().execute(new HttpGet(url));
        InputStream in = response.getEntity().getContent();
        return in;
    }

    private CloseableHttpClient getHttpClient(){
        if(httpClient == null){
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(10);
            cm.setMaxTotal(10);
            httpClient = HttpClients.custom().setConnectionManager(cm)
                    .build();
        }
        return httpClient;

    }
}
