package org.divy.common.bo.endpoint.hatoas.association.builder;

import org.divy.common.bo.dynamic.clazz.Builder;
import org.divy.common.bo.dynamic.clazz.member.method.reader.ReaderBuilder;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.hatoas.association.Association;
import org.divy.common.bo.endpoint.hatoas.association.Cardinality;
import org.divy.common.bo.endpoint.hatoas.association.PropagateSave;
import org.divy.common.bo.rest.AbstractHATOASMapper;

import java.util.Arrays;

public class AssociationBuilder<T extends BusinessObject<I>, I, P extends Builder> extends Association<T, I> implements Builder<P>
{
    private P parent;

    public AssociationBuilder(P parent) {
        this.parent = parent;
    }

    @Override
    public P and()
    {
        return parent;
    }

    public AssociationBuilder<T,I, P> withMapper( AbstractHATOASMapper hatoasMapper) {
        this.hatoasMapper = hatoasMapper;
        return this;
    }

    //Builder methods


    public AssociationBuilder() {
    }

    public AssociationBuilder<T, I, P> manager(BOManager<T, I> manager) {
        this.setManager(manager);
        return this;
    }

    public AssociationBuilder<T, I, P> name(String name) {
        this.setName(name);
        return this;
    }

    public AssociationBuilder<T, I, P> includeInRead() {
        setIncludeInReadOperation(true);
        return this;
    }

    public AssociationBuilder<T, I, P> propagateSave(PropagateSave... propagateSaves) {
        setPropagateSaves(Arrays.asList(propagateSaves));
        return this;
    }

    public AssociationBuilder<T, I, P> cardinality(Cardinality cardinality) {
        this.setCardinality(cardinality);
        return this;
    }



    public <O> O readWith(Class<O> groupClass) {
        ReaderBuilder readerBuilder = new ReaderBuilder(this);
        setReader(readerBuilder);
        return (O) readerBuilder.withMethodOn(groupClass);
    }

    public  <O> O createWith(Class<O> groupClass) {
        CreateBuilder createBuilder = new CreateBuilder();
        setCreate(createBuilder);
        return createBuilder.withMethodOn(groupClass);
    }

    public  <O> O updateWith(Class<O> groupClass) {
        UpdateBuilder updateBuilder = new UpdateBuilder();
        setUpdater(updateBuilder);
        return (O) updateBuilder.withMethodOn(groupClass);
    }


    public ReaderBuilder<AssociationBuilder> attribute(String attributeName) {
        ReaderBuilder<AssociationBuilder> readerBuilder = new ReaderBuilder<>( this );
        setReader(readerBuilder);
        setName(attributeName);
        readerBuilder.attribute(attributeName);
        return readerBuilder;
    }
}
