package org.divy.common.bo.jersey.rest;

import org.divy.common.bo.rest.AbstractLinkBuilderFactory;
import org.divy.common.bo.rest.LinkBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Link;

public class JerseyLinkBuilderFactoryImpl extends AbstractLinkBuilderFactory<Link> {


    public JerseyLinkBuilderFactoryImpl() {
        this(null);
    }

    public JerseyLinkBuilderFactoryImpl(HttpServletRequest request) {
        super(request);
    }

    @Override
    public LinkBuilder<Link> newBuilder() {
        String scheme = DEFAULT_SCHEME;
        var originalPath = "";

        if (request != null) {
            scheme = resolveSchema();
            originalPath = resolveOriginalPath();
        }

        return new JerseyLinkBuilderImpl(scheme, getOriginalHost(), originalPath);
    }
}
