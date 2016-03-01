package com.example.adapter;

import com.example.model.PageModel;
import com.example.model.Sitemap;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public final class SitemapAdapter {

    public static Sitemap adapt(PageModel page) {
        Sitemap sitemap = null;

        if (page != null) {
            sitemap = new Sitemap();
            sitemap.setLoc(page.getUrl());

            if (isNotEmpty(page.getImages())) {
                sitemap.setImages(page.getImages().toArray(new String[page.getImages().size()]));
            }

            if (isNotEmpty(page.getStaticContents())) {
                sitemap.setStaticContents(page.getStaticContents().toArray(new String[page.getStaticContents().size()]));
            }
            if (isNotEmpty(page.getExternalReferences())) {
                sitemap.setExternalReferences(page.getExternalReferences().toArray(new String[page.getExternalReferences().size()]));
            }
        }
        return sitemap;
    }
}