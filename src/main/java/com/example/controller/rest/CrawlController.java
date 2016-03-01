package com.example.controller.rest;

import com.example.api.service.CrawlService;
import com.example.model.ServiceResponse;
import com.example.model.Sitemap;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for managing WebCrawling Endpoints.
 */
@RestController
@Api(value = "Crawl Service Endpoint", description = "Crawler Service")
@RequestMapping(CrawlController.CRAWL)
public class CrawlController {

    /**
     * Hello World URL.
     */
    public static final String CRAWL = "/crawl";


    /**
     * URI Prefix for ID.
     */
    public static final String URL_BY_ID = "/{id}";

    /**
     * The constant JSON.
     */
    public static final String JSON = "application/json";


    /**
     * The constant JSON.
     */
    public static final String XML = "application/xml";

    /**
     * Dao client.
     */
    @Autowired
    private CrawlService crawlService;

    /**
     * Get a SiteMap by Url.
     *
     * @param url the url
     *
     * @return Response Message
     */
    @RequestMapping(value = URL_BY_ID, method = RequestMethod.GET, produces = {JSON, XML})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Crawl the web by the domain", notes = "Get the Sitemap for the domain")
    final ServiceResponse<List<Sitemap>, String> getSiteMap(
            @ApiParam(value = "The web url for crawling")
            @PathVariable final String url) throws Exception {
        List<Sitemap> sitemaps = crawlService.crawlTheWebUrl(url);
        return new ServiceResponse<>(sitemaps, null);
    }

}
