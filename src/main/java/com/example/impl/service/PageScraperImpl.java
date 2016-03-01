package com.example.impl.service;

import com.example.api.service.PageScraper;
import com.example.exception.UrlConnectionException;
import com.example.model.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Set;

import static com.google.common.collect.Sets.newLinkedHashSet;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * DAO client implementation.
 */
@Service
public class PageScraperImpl implements PageScraper {

    /**
     * The constant LOG.
     */
    private static final Logger LOG = getLogger(PageScraperImpl.class);

    @Override
    public PageModel getPageDetails(String rootUrl) throws UrlConnectionException {

        PageModel pageModel = null;
        if (isNoneEmpty(rootUrl)) {

            try {
                URI rootUri = new URI(rootUrl);

                if(isEmpty(rootUri.getScheme())){
                    rootUrl = "http://" + rootUrl;
                }

                Document doc = Jsoup.connect(rootUrl).userAgent("Mozilla").timeout(10000).get();
                if(doc.hasText()){
                    pageModel = new PageModel();
                    pageModel.setTitle(doc.title());
                    pageModel.setUrl(doc.location());

                    Set<String> references = newLinkedHashSet();
                    Set<String> externalUrls = newLinkedHashSet();
                    Set<String> staticContent = newLinkedHashSet();
                    Set<String> images = newLinkedHashSet();

                    Elements media = doc.select("[src]");

                    for (Element src : media) {
                        if (src.tagName().equals("img"))
                            images.add(src.attr("abs:src"));
                    }

                    Elements imports = doc.select("link[href]");
                    for (Element link : imports) {
                        staticContent.add(link.attr("abs:href"));
                    }

                    Elements refs = doc.select("a[href]");

                    URI hostUri = new URI(doc.baseUri());
                    String hostDomain = hostUri.getScheme() + "://" + hostUri.getHost();

                    for (Element ref : refs) {
                        String linkUrl = ref.attr("abs:href");
                        if (startsWith(linkUrl, hostDomain)) {
                            if (equalsIgnoreCase(rootUrl, linkUrl)) {
                                continue;
                            } else {
                                String path = new URI(linkUrl).getPath();
                                if (StringUtils.equals(path, "/") || startsWith(path, "#")) {
                                    continue;
                                }
                                references.add(linkUrl);
                            }
                        } else {
                            externalUrls.add(linkUrl);
                        }
                    }

                    pageModel.setReferences(references);
                    pageModel.setExternalReferences(externalUrls);
                    pageModel.setStaticContents(staticContent);
                    pageModel.setImages(images);
                }

            } catch (MalformedURLException | URISyntaxException | UnknownHostException | SocketTimeoutException ex) {
                LOG.error("The given url cannot be fetched " + rootUrl);
                throw new UrlConnectionException(ex.getMessage());
            } catch (IOException ex) {
                LOG.error("Unexpected exception occured during the fetch of " + rootUrl);
                throw new UrlConnectionException(ex.getMessage());
            }
        }
        return pageModel;
    }

}
