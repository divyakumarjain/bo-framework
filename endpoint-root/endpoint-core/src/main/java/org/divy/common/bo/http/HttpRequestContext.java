package org.divy.common.bo.http;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Holds the servlet request context (the {@link HttpServletRequest} and {@link HttpServletResponse} objects) in a
 * ThreadLocal instance for
 * easy access.
 *
 */
public class HttpRequestContext {

    private static final ThreadLocal<HttpRequestContext> LOCAL_CONTEXT =
            new ThreadLocal<>();

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public HttpRequestContext(ServletRequest req, ServletResponse res) {
        this.request = (HttpServletRequest) req;
        this.response = (HttpServletResponse) res;
    }

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
     * @param ctx the http request context
     */
    public static void setContext(HttpRequestContext ctx) {
        LOCAL_CONTEXT.set(ctx);
    }

    /**
     * Clear the ThreadLocal instance of any existing context.
     */
    public static void clearContext() {
        LOCAL_CONTEXT.remove();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

}
