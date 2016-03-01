package com.example.exception;


/**
 * Generic Crawler exception .
 */
public class CrawlerException extends Exception {
    /**
     * Constructs new CrawlerException with the specified message.
     *
     * @param msg message
     */
    public CrawlerException(final String msg) {
        super(msg);
    }

    /**
     * Constructs a new CrawlerException with the specified detail message and
     * cause.
     *
     * @param msg   message.
     * @param cause cause.
     */
    public CrawlerException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
