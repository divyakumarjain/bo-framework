package org.divy.bo.demos.spring.cards;

import org.divy.bo.demos.spring.cards.pages.Page;
import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.impl.AbstractHATEOASMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
class GreetingCardsHATEOASMapper extends AbstractHATEOASMapper<GreetingCard, GreetingCardRepresentation> {

    public GreetingCardsHATEOASMapper(LinkBuilderFactory linkBuilderFactory) {
        super(GreetingCard.class, GreetingCardRepresentation.class, linkBuilderFactory);
    }

    @Override
    protected void doFillLinks(GreetingCardRepresentation representation, GreetingCard businessObject) {
        representation.addLink(getLinkBuilderFactory().newBuilder()
                .path(GreetingCardEndpoint.class)
                .path(GreetingCardEndpoint.class,"read")
                .buildLink("self", representation.getId()));
    }

    @Override
    protected void doFillAssociations(GreetingCardRepresentation representation, GreetingCard businessObject) {
        List<Page> pages = businessObject.getPages();
        if(CollectionUtils.isEmpty(pages)) {
            representation.addAssociation("pages", pages);
        }
    }

    @Override
    protected void doReadLinks(GreetingCardRepresentation representation, GreetingCard businessObject) {

    }

    @Override
    protected void doReadAssociations(GreetingCardRepresentation representation, GreetingCard businessObject) {

    }
}
