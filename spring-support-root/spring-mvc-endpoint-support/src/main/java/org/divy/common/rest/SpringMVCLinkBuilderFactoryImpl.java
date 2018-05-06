package org.divy.common.rest;

import org.divy.common.bo.rest.AbstractLinkBuilderFactory;
import org.divy.common.bo.rest.LinkBuilder;

import javax.servlet.http.HttpServletRequest;

public class SpringMVCLinkBuilderFactoryImpl extends AbstractLinkBuilderFactory {

    public SpringMVCLinkBuilderFactoryImpl() {
        this(null);
    }

    public SpringMVCLinkBuilderFactoryImpl(HttpServletRequest request) {
        super(request);
    }

    @Override
    public LinkBuilder newBuilder() {
        String scheme = DEFAULT_SCHEME;
        String originalPath = "";

        if (request != null) {
            scheme = resolveSchema();
            originalPath = resolveOriginalPath();
        }

        return new SpringLinkBuilderImpl(scheme, getOriginalHost(), originalPath);
    }

}
