package org.divy.common.bo.endpoint.hatoas.association.builder;

import org.divy.common.bo.endpoint.hatoas.association.Create;

import java.util.Optional;

public class CreateBuilder implements Create {

    public <T> T createWith(Class<T> groupClass) {
        // TODO Auto-generated method stub
        return null;
    }

    public <O> O withMethodOn( Class<O> groupClass )
    {
        return null;
    }

    @Override
    public Optional<Object> createWith( Object source, Object target )
    {
        return Optional.empty();
    }
}
