package org.divy.common.bo.http;

import jakarta.servlet.*;
import java.io.IOException;

public class HttpRequestContextFilter implements Filter {

    @SuppressWarnings("unused")
    private FilterConfig filterConfig;

    /*
     * (non-Javadoc)
     * @see jakarta.servlet.Filter#init(jakarta.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /*
     * (non-Javadoc)
     * @see jakarta.servlet.Filter#doFilter(jakarta.servlet.ServletRequest, jakarta.servlet.ServletResponse,
     * jakarta.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpRequestContext.setNewContext(request, response);
            chain.doFilter(request, response);
        }
        finally {
            HttpRequestContext.clearContext();
        }
    }

    @Override
    public void destroy() {
        //noop
    }
}

