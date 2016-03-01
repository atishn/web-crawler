package com.example.model;

import lombok.Data;

import java.io.Serializable;


/**
 * Sitemap Data Model.
 */
@Data
public class Sitemap implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -7846773639244185780L;


    private String loc;

    private String[] images;

    /**
     * The Static contents.
     */
    private String[] staticContents;

    /**
     * The External references.
     */
    private String[] externalReferences;

}
