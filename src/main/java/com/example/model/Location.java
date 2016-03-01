package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * Sitemap Data Model.
 */
@Data
@AllArgsConstructor
public class Location implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -7846773639244185780L;

    private String loc;

}
