/**
 * Copyright (C) 2012, Intellectual Reserve, Inc. All rights reserved.
 */
package org.divy.common.http;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Holds the servlet request context (the {@link HttpServletRequest} and {@link HttpServletResponse} objects) in a
 * ThreadLocal instance for
 * easy access.
 *
 */
public class HttpRequestContext {

    private static final ThreadLocal<HttpRequestContext> LOCAL_CONTEXT =
            new ThreadLocal<HttpRequestContext>();

    /**
     * Return the local HttpRequestContext or null if thread is not
     * a request service thread.
     *
     * @return local HttpRequestContext or null
     */
    public static HttpRequestContext getContext() {
        return LOCAL_CONTEXT.get();
    }

    /**
     * Convenience method to return the HttpServletRequest object
     * from the local HttpRequestContext. This returns null if the
     * thread is not a request service thread.
     *
     * @return local HttpServletRequest or null
     */
    public static HttpServletRequest request() {
        HttpRequestContext ctx = getContext();
        if (ctx != null) {
            return ctx.getRequest();
        }

        return null;
    }

    /**
     * Convenience method to return the HttpServletResponse object
     * from the local HttpRequestContext. This returns null if the
     * thread is not a request service thread.
     *
     * @return local HttpServletResponse or null
     */
    public static HttpServletResponse response() {
        HttpRequestContext ctx = getContext();
        if (ctx != null) {
            return ctx.getResponse();
        }

        return null;
    }

    /**
     * Create a new HttpRequestContext and store it in the ThreadLocal instance.
     *
     * @param req ServletRequest
     * @param res ServletResponse
     */
    public static void setNewContext(ServletRequest req, ServletResponse res) {
        setContext(new HttpRequestContext(req, res));
    }

    /**
     * Store the HttpRequestContext in the ThreadLocal instance.
     *
     * @param ctx
     */
    public static void setContext(HttpRequestContext ctx) {
        LOCAL_CONTEXT.set(ctx);
    }

    /**
     * Clear the ThreadLocal instance of any existing context.
     */
    public static void clearContext() {
        LOCAL_CONTEXT.set(null);
    }

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public HttpRequestContext(ServletRequest req, ServletResponse res) {
        this.request = (HttpServletRequest) req;
        this.response = (HttpServletResponse) res;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

}
