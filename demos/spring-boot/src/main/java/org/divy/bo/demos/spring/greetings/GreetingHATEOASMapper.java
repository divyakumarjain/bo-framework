package org.divy.bo.demos.spring.greetings;


import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.impl.AbstractHATEOASMapper;
import org.springframework.stereotype.Component;

@Component
class GreetingHATEOASMapper extends AbstractHATEOASMapper<Greeting, GreetingRepresentation> {

    public GreetingHATEOASMapper(LinkBuilderFactory linkBuilderFactory) {
        super(Greeting.class, GreetingRepresentation.class, linkBuilderFactory);
    }

    @Override
    protected void doFillLinks(GreetingRepresentation representation, Greeting businessObject) {
        representation.addLink(getLinkBuilderFactory().newBuilder()
                .path(GreetingsEndpoint.class)
                .path(GreetingsEndpoint.class,"read")
                .buildLink("self", representation.getId()));
    }

    @Override
    protected void doFillAssociations(GreetingRepresentation representation, Greeting businessObject) {

    }

    @Override
    protected void doReadLinks(GreetingRepresentation representation, Greeting businessObject) {

    }

    @Override
    protected void doReadAssociations(GreetingRepresentation representation, Greeting businessObject) {

    }
}
