package org.divy.common.bo.http;

import javax.servlet.*;
import java.io.IOException;

public class HttpRequestContextFilter implements Filter {

    @SuppressWarnings("unused")
    private FilterConfig filterConfig;

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
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

