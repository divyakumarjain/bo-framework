package org.divy.bo.demos.spring.greetings;


import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.impl.AbstractHATEOASMapper;
import org.springframework.stereotype.Component;

@Component
class GreetingHATEOASMapper extends AbstractHATEOASMapper<GreetingEntity, GreetingRepresentation> {

    public GreetingHATEOASMapper(LinkBuilderFactory linkBuilderFactory) {
        super(GreetingEntity.class, GreetingRepresentation.class, linkBuilderFactory);
    }

    @Override
    protected void doFillLinks(GreetingRepresentation representation, GreetingEntity businessObject) {
        representation.addLink(getLinkBuilderFactory().newBuilder()
                .path(GreetingsEndpoint.class)
                .path(GreetingsEndpoint.class,"read")
                .buildLink("self", representation.getId()));
    }

    @Override
    protected void doFillAssociations(GreetingRepresentation representation, GreetingEntity businessObject) {

    }

    @Override
    protected void doReadLinks(GreetingRepresentation representation, GreetingEntity businessObject) {

    }

    @Override
    protected void doReadAssociations(GreetingRepresentation representation, GreetingEntity businessObject) {

    }
}
