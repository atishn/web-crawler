package com.example.exception;


/**
 * Exception thrown when incoming url cannot be reached.
 */
public class UrlConnectionException extends CrawlerException {

    /**
     * Constructs new UrlConnectionException with the specified message.
     *
     * @param msg message
     */
    public UrlConnectionException(final String msg) {
        super(msg);
    }

    /**
     * Constructs a new UrlConnectionException exception with the
     * specified detail message and cause.
     *
     * @param msg   message.
     * @param cause cause.
     */
    public UrlConnectionException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
