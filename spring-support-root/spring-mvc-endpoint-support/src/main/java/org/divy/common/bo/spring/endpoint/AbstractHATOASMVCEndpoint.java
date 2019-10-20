package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.hatoas.AbstractHATOASEndpoint;
import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * The Abstract implementation for HATEOAS based Business Object management Endpoint
 *
 * @param <E> The Business Object Entity
 * @param <R> The HATEOAS representation of Business Object entity
 *
 */
public abstract class AbstractHATOASMVCEndpoint<E extends BusinessObject<I>,
        R extends Representation,
        I extends Serializable>
        extends AbstractHATOASEndpoint<E, R, I, ResponseEntity>
{

    public AbstractHATOASMVCEndpoint(ResponseEntityBuilderFactory<R, ResponseEntity> responseEntityBuilderFactory, AssociationsHandler<E,I> associationsHandler) {
        super(responseEntityBuilderFactory, associationsHandler);
    }

    @Override
    public final ResponseEntity create(R businessObject)
    {
        return super.create(businessObject);
    }

    @Override
    public final ResponseEntity update(I id, R businessObject) {
        return super.update(id, businessObject);
    }

    @Override
    public final ResponseEntity delete(I id) {

        return super.delete(id);

    }

    @Override
    public final ResponseEntity list() {
        return super.list();

    }

    @Override
    public final ResponseEntity search(Query query) {
        return super.search(query);
    }

    @Override
    public final ResponseEntity read(I id) {
        return super.read(id);
    }

    @RequestMapping(method = RequestMethod.GET, value =  "/{id}/{relation}")
    @Override
    public ResponseEntity readRelation(@PathVariable("id") I id,
                                 @PathVariable("relation")String relation) {

        return super.readRelation(id, relation);
    }

    /**
     * Creates Relationship between Business Object Entities.
     *
     * @param id Business Object Entity identity of source of the relationship/association
     * @param relation name of the association attribute of relationship
     *
     * @return returns 201 status code for successful creation of relationship/association
     */
    @RequestMapping(method = RequestMethod.POST ,value = "/{id}/{relation}")
    @Override
    public ResponseEntity createRelation(@PathVariable("id") I id,
                                   @PathVariable("relation")String relation,
                                   @RequestBody R representation) {

        return super.createRelation(id, relation, representation);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/{relation}")
    @Override
    public ResponseEntity updateRelation(I id,
                                         @PathVariable("relation") String relation) {
        return super.updateRelation(id, relation);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/{relation}")
    @Override
    public ResponseEntity deleteRelation(I id,
          @PathVariable("relation") String relation) {
        return super.deleteRelation(id, relation);
    }
}
