package com.example.configuration;

import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Filter that will strip all special character from request param values.
 */
@Component
public class XSSFilter extends GenericFilterBean {
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp,
                         final FilterChain chain) throws IOException, ServletException {
        assert req instanceof HttpServletRequest;
        chain.doFilter(
                new XSSSafeHttpServletRequestWrapper((HttpServletRequest) req),
                resp);
    }

    /**
     * Servlet request wrapper that will run all request params through whitelist before returning.
     */
    private static class XSSSafeHttpServletRequestWrapper extends HttpServletRequestWrapper {
        /**
         * Constructor to match super class.
         *
         * @param req req to be wrapped.
         */
        public XSSSafeHttpServletRequestWrapper(final HttpServletRequest req) {
            super(req);
        }

        @Override
        public String[] getParameterValues(final String name) {
            String[] values = super.getParameterValues(name);
            if (ArrayUtils.isEmpty(values)) {
                return values;
            }
            return Arrays.asList(values).stream()
                    .map(value -> Jsoup.clean(value, Whitelist.none()))
                    .collect(Collectors.toList()).toArray(new String[values.length]);
        }
    }
}
