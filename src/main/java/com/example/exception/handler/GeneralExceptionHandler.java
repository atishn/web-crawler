package com.example.exception.handler;

import com.example.exception.CrawlerException;
import com.example.exception.UrlConnectionException;
import com.example.model.ErrorResponse;
import com.example.model.ServiceResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.valueOf;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * The type General exception handler.
 */
@ControllerAdvice
public class GeneralExceptionHandler {
    /**
     * The constant LOG.
     */
    private static final Logger LOG = getLogger(GeneralExceptionHandler.class);


    /**
     * Handle crawler exception.
     *
     * @param ex   the ex
     * @param req  the req
     * @param resp the resp
     *
     * @return the service response
     */
    @ExceptionHandler(CrawlerException.class)
    @ResponseBody
    ServiceResponse<String, ErrorResponse> handleCrawlerException(
            final CrawlerException ex,
            final HttpServletRequest req,
            final HttpServletResponse resp) {

        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ErrorResponse error = new ErrorResponse();
        error.setCode(valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setTitle("Unexpected Exception.");
        error.setLink(req.getRequestURL().toString());
        error.setDetail(ex.getMessage());

        return new ServiceResponse(null, error);
    }


    /**
     * Handle general exception.
     *
     * @param ex   the ex
     * @param req  the req
     * @param resp the resp
     *
     * @return the service response
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ServiceResponse<String, ErrorResponse> handleGeneralException(
            final Exception ex, final HttpServletRequest req,
            final HttpServletResponse resp) {

        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ErrorResponse error = new ErrorResponse();
        error.setCode(valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setTitle("Unknown Exception. Please try again later.");
        error.setLink(req.getRequestURL().toString());
        error.setDetail(ex.getMessage());

        return new ServiceResponse(null, error);
    }


    /**
     * Handle invalid argument exception.
     *
     * @param ex   the ex
     * @param req  the req
     * @param resp the resp
     *
     * @return the service response
     */
    @ExceptionHandler(UrlConnectionException.class)
    @ResponseBody
    ServiceResponse<String, ErrorResponse> handleInvalidArgumentException(
            final UrlConnectionException ex, final HttpServletRequest req,
            final HttpServletResponse resp) {

        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        ErrorResponse error = new ErrorResponse();
        error.setCode(valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setTitle("Invalid Request Data. Please verify.");
        error.setLink(req.getRequestURL().toString());
        error.setDetail(ex.getMessage());
        return new ServiceResponse(null, error);
    }

}
