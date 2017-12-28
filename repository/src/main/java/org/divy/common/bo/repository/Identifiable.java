package org.divy.common.bo.repository;


public interface Identifiable<I> {
    I identity();

    String _type();
}
