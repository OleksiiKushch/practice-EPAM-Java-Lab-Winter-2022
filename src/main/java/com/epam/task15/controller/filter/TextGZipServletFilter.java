package com.epam.task15.controller.filter;

import com.epam.task15.controller.util.GZipServletResponseWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TextGZipServletFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(TextGZipServletFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)  servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (acceptsGZipEncoding(request) && isTextContentType(request)) {
            response.addHeader("Content-Encoding", "gzip");
             GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(response);
            filterChain.doFilter(servletRequest, gzipResponse);
             gzipResponse.close();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean acceptsGZipEncoding(HttpServletRequest request) {
        String acceptEncoding = request.getHeader("Accept-Encoding");
        LOG.debug("Accept-Encoding: " + acceptEncoding);
        return StringUtils.isNotEmpty(acceptEncoding) && acceptEncoding.contains("gzip");
    }

    private boolean isTextContentType(HttpServletRequest request) {
        String contentType = request.getHeader("Content-Type");
        LOG.debug("Content-Type: " + contentType);
        return StringUtils.isNotEmpty(contentType) && contentType.contains("text");
    }
}
