package org.divy.common.bo.rest;

public interface LinkBuilderFactory<L> {
    LinkBuilder<L> newBuilder();
}
