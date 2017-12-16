package org.divy.common.bo.endpoint.hypermedia;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.business.validation.BOValidationException;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.endpoint.hypermedia.association.AbstractAssociations;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.HyperMediaMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.exception.NotFoundException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class AbstractHyperMediaEndpoint<B extends BusinessObject<I>
        , E extends Representation
        , I extends Serializable
        , R>
        extends AbstractCRUDEndpoint<E, I, R> {

    AbstractHyperMediaEndpoint(ResponseEntityBuilderFactory<E, R> responseEntityBuilderFactory) {
        super(responseEntityBuilderFactory);
    }

    public abstract BOManager<B, I> getManager();

    R updateRelation(I id, String relation) {
        //TODO Implement
        return responseEntityBuilderFactory.update().build();
    }

    public R readRelation(I id, String relation) {

        B entity = getManager().get(id);

        Optional<Object> optionalEntityRelation = this.getAssociations().getAssociation(relation).read(entity);

        if(optionalEntityRelation.isPresent()) {
            return responseEntityBuilderFactory.read((E)optionalEntityRelation.get()).build();
        } else {
            return responseEntityBuilderFactory.read().build();
        }
    }

    /**
     * Creates Relationship between Business Object Entities.
     *
     * @param id Business Object Entity identity of source of the relationship/association
     * @param relation name of the association attribute of relationship
     *
     * @return returns 201 status code for successful creation of relationship/association
     */
    public R createRelation(@NotNull I id
                            ,String relation) {

        B businessObject = getManager().get(id);

        if (businessObject == null) {
            throw new NotFoundException("Could not find the entity");
        }

        //TODO Implement

        return responseEntityBuilderFactory.create(this.mapFromBO(businessObject)).build();
    }

    @Override
    protected String identity(E representation) {
        return representation.identity().toString();
    }

    @Override
    protected E doRead(I id) {
        B businessObject = getManager().get(id);

        if(businessObject == null) {
            throw new NotFoundException("Could not find the entity");
        }

        return mapFromBO(businessObject);
    }

    @Override
    protected E doCreate(E representation) throws BOValidationException
    {
        B createdBusinessObject = getManager().create(mapToBO(representation));
        return mapFromBO(createdBusinessObject);
    }

    @Override
    protected E doUpdate(I id, E representation) {
        return mapFromBO(getManager().update(id, mapToBO(representation)));
    }

    @Override
    protected E doDelete(I id) {
        return mapFromBO(getManager().deleteById(id));
    }

    @Override
    protected Collection<E> doList() {
        Collection<B> boList = getManager().list();

        Collection<B> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return getRepresentationMapper().createRepresentationFromBO(resultList);
    }

    @Override
    protected Collection<E> doSearch(Query query) {
        Collection<B> boList = getManager().search(query);

        Collection<B> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return getRepresentationMapper().createRepresentationFromBO(resultList);
    }

    private B mapToBO(E representation) {
        return getRepresentationMapper().createBOFromRepresentation(representation);
    }

    private E mapFromBO(B createdBusinessObject) {
        return getRepresentationMapper().createRepresentationFromBO(createdBusinessObject);
    }

    public abstract HyperMediaMapper<B, E> getRepresentationMapper();

    public abstract AbstractAssociations<B> getAssociations();
}
