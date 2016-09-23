package org.divy.bo.demos.spring.cards;

import org.divy.bo.demos.spring.cards.pages.PagesEntity;
import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.impl.AbstractHATEOASMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
class GreetingCardsHATEOASMapper extends AbstractHATEOASMapper<GreetingCardEntity, GreetingCardRepresentation> {

    public GreetingCardsHATEOASMapper(LinkBuilderFactory linkBuilderFactory) {
        super(GreetingCardEntity.class, GreetingCardRepresentation.class, linkBuilderFactory);
    }

    @Override
    protected void doFillLinks(GreetingCardRepresentation representation, GreetingCardEntity businessObject) {
        representation.addLink(getLinkBuilderFactory().newBuilder()
                .path(GreetingCardEndpoint.class)
                .path(GreetingCardEndpoint.class,"read")
                .buildLink("self", representation.getId()));
    }

    @Override
    protected void doFillAssociations(GreetingCardRepresentation representation, GreetingCardEntity businessObject) {
        List<PagesEntity> pages = businessObject.getPages();
        if(CollectionUtils.isEmpty(pages)) {
            representation.addAssociation("pages", pages);
        }
    }

    @Override
    protected void doReadLinks(GreetingCardRepresentation representation, GreetingCardEntity businessObject) {

    }

    @Override
    protected void doReadAssociations(GreetingCardRepresentation representation, GreetingCardEntity businessObject) {

    }

    @Override
    protected Mapper createMapper() {
        return super.createMapper();
    }
}
