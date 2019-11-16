package org.divy.common.bo.endpoint.test;

import org.divy.common.bo.rest.LinkBuilder;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.spring.jersey.rest.JerseyLinkBuilderImpl;

public class MockLinkBuilderFactory implements LinkBuilderFactory {

    private final String scheme;
    private final String host;
    private final String basePath;

    public MockLinkBuilderFactory(String scheme, String host, String basePath) {
        this.scheme = scheme;
        this.host = host;
        this.basePath = basePath;
    }

    @Override
    public LinkBuilder newBuilder() {
        return new JerseyLinkBuilderImpl(this.scheme, this.host, this.basePath);
    }
}
