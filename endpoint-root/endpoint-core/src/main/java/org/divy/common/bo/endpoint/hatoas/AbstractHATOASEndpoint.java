package org.divy.common.bo.endpoint.hatoas;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.endpoint.hatoas.association.Association;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.HATOASMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.exception.NotFoundException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

public abstract class AbstractHATOASEndpoint<B extends BusinessObject<I>
        , E extends Representation
        , I extends Serializable
        , R>
        extends AbstractCRUDEndpoint<E, I, R> {

    private final AssociationsHandler<B,I> associationsHandler;

    public AbstractHATOASEndpoint(ResponseEntityBuilderFactory<E, R> responseEntityBuilderFactory, AssociationsHandler<B,I> associationsHandler) {
        super(responseEntityBuilderFactory);
        this.associationsHandler = associationsHandler;
    }

    protected R updateRelation(I id, String relation) {
        //TODO implement Update Relationship
        return responseEntityBuilderFactory.update().build();
    }

    protected R deleteRelation(I id, String relation) {
        //TODO implement Delete Relationship
        return responseEntityBuilderFactory.update().build();
    }

    public R readRelation(I id, String relation) {

        B entity = getManager().get(id).orElseThrow( ()->new NotFoundException("Could not find the entity") );

        final Association<B, I> association = this.getAssociationsHandler().getAssociation(relation)
                .orElseThrow(() -> new NotFoundException("Association " + relation + " not found"));

        Optional<Object> optionalEntityRelation = association.read(entity);

        if (optionalEntityRelation.isPresent()) {
            final Object result = optionalEntityRelation.get();
            if(result instanceof Collection)
            {
                return responseEntityBuilderFactory.list((Collection<E>) result).build();
            } else if (result instanceof Representation) {
                return responseEntityBuilderFactory.read((E)result).build();
            }
        }

        return responseEntityBuilderFactory.read().build();
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
                            , String relation
                            , E respresentation) {

        B businessObject = getManager().get(id).orElseThrow( () -> new NotFoundException("Could not find the entity") );

        if (businessObject == null) {
            throw new NotFoundException("Could not find the entity");
        }

        final Association<B, I> association = this.getAssociationsHandler().getAssociation(relation)
                .orElseThrow(() -> new NotFoundException("Association " + relation + " not found"));

        association.create(businessObject, respresentation);

        Optional<Object> optionalEntityRelation = association.read(businessObject);

        if (optionalEntityRelation.isPresent()) {
            final Object result = optionalEntityRelation.get();
            return responseEntityBuilderFactory.read((E)result).build();
        }

        return responseEntityBuilderFactory.read().build();
    }

    @Override
    protected String identity(E representation) {
        return representation.identity().toString();
    }

    @Override
    protected E doRead(I id) {
        var businessObject = getManager().get(id).orElseThrow( ()-> new NotFoundException("Could not find the entity") );
        return mapFromBO(businessObject);
    }

    @Override
    protected E doCreate(E representation)
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

        Collection<B> resultList = new ArrayList<>(boList);

        return getRepresentationMapper().createFromBO(resultList);
    }

    @Override
    protected Collection<E> doSearch(Query query) {
        Collection<B> boList = getManager().search(query);

        Collection<B> resultList = new ArrayList<>(boList);

        return getRepresentationMapper().createFromBO(resultList);
    }

    private B mapToBO(E representation) {
        return getRepresentationMapper().createBO(representation);
    }

    private E mapFromBO(B createdBusinessObject) {
        return getRepresentationMapper().createFromBO(createdBusinessObject);
    }

    public abstract BOManager<B, I> getManager();

    public AssociationsHandler<B,I> getAssociationsHandler() {
        return associationsHandler;
    }

    public abstract HATOASMapper<B, E> getRepresentationMapper();
}
