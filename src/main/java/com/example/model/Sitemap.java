package com.example.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * Sitemap Data Model.
 */
@Data
public class Sitemap implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -7846773639244185780L;

    /**
     * The Url.
     */
    private List<Location> url;

    /**
     * The Images.
     */
    private List<Location> images;

    /**
     * The Static contents.
     */
    private List<Location> staticContents;

    /**
     * The External references.
     */
    private List<Location> externalReferences;

}
