package org.divy.common.bo.spring.mvc.graphql.endpoint;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.hatoas.AbstractHATOASEndpoint;
import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

/**
 * The Abstract implementation for HATEOAS based Business Object management Endpoint
 *
 * @param <E> The Business Object Entity
 * @param <R> The HATEOAS representation of Business Object entity
 *
 */
public abstract class AbstractHATOASMVCEndpoint<E extends BusinessObject<I>,
        R extends Representation <I, Map<String, Object>, L>,
        I extends Serializable,
        L>
        extends AbstractHATOASEndpoint<E, R, I, ResponseEntity<R>, L>
{

    protected AbstractHATOASMVCEndpoint(ResponseEntityBuilderFactory<R, ResponseEntity<R>> responseEntityBuilderFactory, AssociationsHandler<E,I, L> associationsHandler) {
        super(responseEntityBuilderFactory, associationsHandler);
    }

    @Override
    public final ResponseEntity<R> create(R businessObject)
    {
        return super.create(businessObject);
    }

    @Override
    public final ResponseEntity<R> update(I id, R businessObject) {
        return super.update(id, businessObject);
    }

    @Override
    public final ResponseEntity<R> delete(I id) {

        return super.delete(id);

    }

    @Override
    public final ResponseEntity<R> list() {
        return super.list();

    }

    @Override
    public final ResponseEntity<R> search(Query query) {
        return super.search(query);
    }

    @Override
    public final ResponseEntity<R> read(I id) {
        return super.read(id);
    }

    @GetMapping("/{id}/{relation}")
    @Override
    public ResponseEntity<R> readRelation(@PathVariable("id") I id,
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
    @PostMapping("/{id}/{relation}")
    @Override
    public ResponseEntity<R> createRelation(@PathVariable("id") I id,
                                   @PathVariable("relation")String relation,
                                   @RequestBody R representation) {

        return super.createRelation(id, relation, representation);
    }

    @PutMapping("/{id}/{relation}")
    @Override
    public ResponseEntity<R> updateRelation(I id,
                                         @PathVariable("relation") String relation) {
        return super.updateRelation(id, relation);
    }

    @DeleteMapping("/{id}/{relation}")
    @Override
    public ResponseEntity<R> deleteRelation(I id,
          @PathVariable("relation") String relation) {
        return super.deleteRelation(id, relation);
    }
}
