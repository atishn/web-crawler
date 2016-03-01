package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;


/**
 * Sitemap Data Model.
 */
@Data
public class PageModel implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -7846773639244185780L;

    private String title;

    private String url;

    private Set<String> references;

    private Set<String> images;

    private Set<String> staticContents;

    /**
     * The External references.
     */
    private Set<String> externalReferences;

}
