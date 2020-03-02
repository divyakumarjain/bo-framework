package org.divy.common.bo.spring.mvc.rest;

import org.divy.common.bo.rest.AbstractLinkBuilderFactory;
import org.divy.common.bo.rest.LinkBuilder;
import org.springframework.hateoas.Link;

import jakarta.servlet.http.HttpServletRequest;

public class SpringMVCLinkBuilderFactoryImpl extends AbstractLinkBuilderFactory {

    public SpringMVCLinkBuilderFactoryImpl() {
        this(null);
    }

    public SpringMVCLinkBuilderFactoryImpl(HttpServletRequest request) {
        super(request);
    }

    @Override
    public LinkBuilder<Link> newBuilder() {
        String scheme = DEFAULT_SCHEME;
        String originalPath = "";

        if (request != null) {
            scheme = resolveSchema();
            originalPath = resolveOriginalPath();
        }

        return new SpringLinkBuilderImpl(scheme, getOriginalHost(), originalPath);
    }

}
