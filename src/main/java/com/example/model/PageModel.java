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

    /**
     * The Title.
     */
    private String title;

    /**
     * The Url.
     */
    private String url;

    /**
     * The References.
     */
    private Set<String> references;

    /**
     * The Images.
     */
    private Set<String> images;

    /**
     * The Static contents.
     */
    private Set<String> staticContents;

    /**
     * The External references.
     */
    private Set<String> externalReferences;

}
