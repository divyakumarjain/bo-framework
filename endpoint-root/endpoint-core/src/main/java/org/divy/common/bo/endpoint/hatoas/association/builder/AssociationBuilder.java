package org.divy.common.bo.endpoint.hatoas.association.builder;

import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.Builder;
import org.divy.common.bo.dynamic.clazz.member.method.reader.ReaderBuilder;
import org.divy.common.bo.dynamic.clazz.member.method.setter.SetterBuilder;
import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.endpoint.hatoas.association.Association;
import org.divy.common.bo.endpoint.hatoas.association.Cardinality;
import org.divy.common.bo.endpoint.hatoas.association.PropagateSave;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.AbstractHATOASMapper;

import java.util.Arrays;
import java.util.Map;

public class AssociationBuilder<T extends BusinessObject<I>, I, L> extends Association<T, I, L> implements Builder
{
    private String targetMethodName;

    public AssociationBuilder<T, I, L> withMapper( AbstractHATOASMapper<T, I, Representation<I, Map<String, Object>, L >, L> hatoasMapper) {
        this.mapper = hatoasMapper;
        return this;
    }

    public AssociationBuilder<T, I, L> manager(BOManager<T, I> manager) {
        this.setManager(manager);
        return this;
    }

    public AssociationBuilder<T, I, L> name(String name) {
        this.setName(name);
        return this;
    }

    public AssociationBuilder<T, I, L> includeInRead() {
        setIncludeInReadOperation(true);
        return this;
    }

    public AssociationBuilder<T, I, L> propagateSave(PropagateSave... propagateSaves) {
        setPropagateSaves(Arrays.asList(propagateSaves));
        return this;
    }

    public AssociationBuilder<T, I, L> cardinality(Cardinality cardinality) {
        this.setCardinality(cardinality);
        return this;
    }

    public <O> O readWith(Class<O> groupClass) {
        var readerBuilder = new ReaderBuilder(this);
        setReader(readerBuilder);
        return (O) readerBuilder.withMethodOn(groupClass);
    }

    public  <O> O createWith(Class<O> groupClass) {
        var createBuilder = new SetterBuilder();
        setSetter(createBuilder);
        return (O)createBuilder.withMethodOn(groupClass);
    }


    public ReaderBuilder<AssociationBuilder<T,I, L>> attribute(String attributeName) {
        ReaderBuilder<AssociationBuilder<T,I, L>> readerBuilder = new ReaderBuilder<>( this );
        setReader(readerBuilder);
        setName(attributeName);
        readerBuilder.attribute(attributeName);
        return readerBuilder;
    }

    public String getTargetMethodName() {
        return targetMethodName;
    }

    public AssociationBuilder<T, I, L> withTargetMethodName(String targetMethodName) {
        this.targetMethodName = targetMethodName;
        return this;
    }
}
