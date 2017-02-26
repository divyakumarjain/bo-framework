package org.divy.common.bo.endpoint.test;

import org.divy.common.rest.LinkBuilder;
import org.divy.common.rest.LinkBuilderFactory;

import javax.ws.rs.core.UriInfo;

public class MockLinkBuilderFactory implements LinkBuilderFactory {

    private String scheme;
    private String host;
    private String basePath;

    public MockLinkBuilderFactory(String scheme, String host, String basePath) {
        this.scheme = scheme;
        this.host = host;
        this.basePath = basePath;
    }

    @Override
    public LinkBuilder newBuilder() {
        return new LinkBuilder(this.scheme, this.host, this.basePath);
    }

    @Override
    public LinkBuilder newBuilder(UriInfo requestUriInfo) {
        return new LinkBuilder(this.scheme, this.host, this.basePath);
    }
}
