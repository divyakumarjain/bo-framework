package org.divy.common.bo.endpoint.hypermedia.association.builder;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.hypermedia.association.Association;
import org.divy.common.bo.endpoint.hypermedia.association.AttributeReader;
import org.divy.common.bo.endpoint.hypermedia.association.Cardinality;
import org.divy.common.bo.endpoint.hypermedia.association.PropagateSave;
import org.divy.common.bo.endpoint.hypermedia.association.reader.ReaderBuilder;
import org.divy.common.bo.rest.AbstractHyperMediaMapper;

import java.util.Arrays;

public class AssociationBuilder<T extends BusinessObject<I>, I> extends Association<T, I>
{
    //Builder methods

    public AssociationBuilder<T,I> withMapper(AbstractHyperMediaMapper hyperMediaMapper) {
        this.hyperMediaMapper = hyperMediaMapper;
        return this;
    }

    public AssociationBuilder<T, I> manager(BOManager<T, I> manager) {
        this.setManager(manager);
        return this;
    }

    public AssociationBuilder<T, I> name(String name) {
        this.setName(name);
        return this;
    }

    public AssociationBuilder<T, I> includeInRead() {
        setIncludeInReadOperation(true);
        return this;
    }

    public AssociationBuilder<T, I> propagateSave(PropagateSave... propagateSaves) {
        setPropagateSaves(Arrays.asList(propagateSaves));
        return this;
    }

    public AssociationBuilder<T, I> cardinality(Cardinality cardinality) {
        this.setCardinality(cardinality);
        return this;
    }



    public T readWith(Class<T> groupClass) {
        ReaderBuilder readerBuilder = new ReaderBuilder();
        setReader(readerBuilder);
        return readerBuilder.withMethodOn(groupClass);
    }

    public T createWith(Class<T> groupClass) {
        CreateBuilder createBuilder = new CreateBuilder();
        setCreate(createBuilder);
        return createBuilder.createWith(groupClass);
    }

    public T updateWith(Class<T> groupClass) {
        UpdateBuilder updateBuilder = new UpdateBuilder();
        setUpdater(updateBuilder);
        return updateBuilder.updateWith(groupClass);
    }


    public AttributeReader attribute(String attributeName) {
        ReaderBuilder readerBuilder = new ReaderBuilder();
        setReader(readerBuilder);
        setName(attributeName);
        return readerBuilder.attribute(attributeName);
    }
}
