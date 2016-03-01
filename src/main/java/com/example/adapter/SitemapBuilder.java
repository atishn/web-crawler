package com.example.adapter;

import com.example.model.Location;
import com.example.model.Sitemap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

/**
 * The type Sitemap builder.
 */
public final class SitemapBuilder {
    /**
     * Instantiates a new Sitemap builder.
     */
    private SitemapBuilder() {
    }

    /**
     * Build sitemap.
     *
     * @param urls           the urls
     * @param images         the images
     * @param staticContents the static contents
     * @param externalRef    the external ref
     *
     * @return the sitemap
     */
    public static Sitemap build(final Collection<String> urls, final Collection<String> images,
                                final Collection<String> staticContents, final Collection<String> externalRef) {
        Sitemap sitemap = null;

        if (isNotEmpty(urls)) {
            sitemap = new Sitemap();

            List<Location> pagesVisited = new ArrayList<>();
            for (String url : urls) {
                pagesVisited.add(new Location(url));
            }
            sitemap.setUrl(pagesVisited);

            if (isNotEmpty(images)) {
                List<Location> imagesRefs = new ArrayList<>();
                for (String image : images) {
                    imagesRefs.add(new Location(image));
                }
                sitemap.setImages(imagesRefs);
            }

            if (isNotEmpty(staticContents)) {
                List<Location> contents = new ArrayList<>();
                for (String content : staticContents) {
                    contents.add(new Location(content));
                }
                sitemap.setStaticContents(contents);
            }

            if (isNotEmpty(externalRef)) {
                List<Location> extRefs = new ArrayList<>();
                for (String ref : externalRef) {
                    extRefs.add(new Location(ref));
                }
                sitemap.setExternalReferences(extRefs);
            }
        }
        return sitemap;
    }
}
