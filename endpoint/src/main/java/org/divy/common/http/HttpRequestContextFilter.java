package org.divy.common.http;

import java.io.IOException;
import javax.servlet.*;

public class HttpRequestContextFilter implements Filter {

    @SuppressWarnings("unused")
    private FilterConfig filterConfig;

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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

