package org.divy.common.bo.database.proxy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.Bindable;
import jakarta.persistence.metamodel.PluralAttribute;
import jakarta.persistence.metamodel.Type;

import javax.persistence.criteria.Expression;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProxyUtil {
    public static <S, T> T[] convertArrayProxy(S[] source, Function<S,T> function) {
        return (T[]) Arrays.stream(source).map( function ).collect( Collectors.toList() ).toArray();
    }

    public static <S, T> List<T> convertListProxy( List<S> source, Function<S,T> function )
    {
        return source.stream().map( function ).collect( Collectors.toList() );
    }

    public static JoinType convertJoinType( javax.persistence.criteria.JoinType source )
    {
        JoinType target;
        switch( source ) {
            case LEFT -> target = JoinType.LEFT;
            case RIGHT -> target = JoinType.RIGHT;
            case INNER -> target = JoinType.INNER;
            default -> throw new IllegalArgumentException("Could not name Enum type " + source.name());
        }

        return target;
    }

    public static javax.persistence.criteria.JoinType convertJoinType( JoinType source )
    {
        javax.persistence.criteria.JoinType target;
        switch( source ) {
            case LEFT -> target = javax.persistence.criteria.JoinType.LEFT;
            case RIGHT -> target = javax.persistence.criteria.JoinType.RIGHT;
            case INNER -> target = javax.persistence.criteria.JoinType.INNER;
            default -> throw new IllegalArgumentException("Could not name Enum type " + source.name());
        }

        return target;
    }

    public static <S,T> Set<T> convertSetProxy( Set<S> source, Function<S,T> function)
    {
        return source.stream().map( function ).collect( Collectors.toSet() );
    }

    public static Attribute.PersistentAttributeType convertPersistentAttributeTypeEnum( javax.persistence.metamodel.Attribute.PersistentAttributeType source )
    {
        Attribute.PersistentAttributeType target;
        switch( source ) {
            case BASIC -> target = Attribute.PersistentAttributeType.BASIC;
            case ELEMENT_COLLECTION -> target = Attribute.PersistentAttributeType.ELEMENT_COLLECTION;
            case EMBEDDED -> target = Attribute.PersistentAttributeType.EMBEDDED;
            case MANY_TO_MANY -> target = Attribute.PersistentAttributeType.MANY_TO_MANY;
            case MANY_TO_ONE -> target = Attribute.PersistentAttributeType.MANY_TO_ONE;
            case ONE_TO_MANY -> target = Attribute.PersistentAttributeType.ONE_TO_MANY;
            case ONE_TO_ONE -> target = Attribute.PersistentAttributeType.ONE_TO_ONE;
            default -> throw new IllegalArgumentException("Could not name Enum type " + source.name());
        }

        return target;
    }

    public static Predicate.BooleanOperator convertBooleanOperatorEnum( javax.persistence.criteria.Predicate.BooleanOperator source )
    {
        Predicate.BooleanOperator target;
        switch( source ) {
            case AND -> target = Predicate.BooleanOperator.AND;
            case OR -> target = Predicate.BooleanOperator.OR;
            default -> throw new IllegalArgumentException("Could not name Enum type " + source.name());
        }

        return target;
    }

    public static PluralAttribute.CollectionType convertCollectionTypeEnum( javax.persistence.metamodel.PluralAttribute.CollectionType source )
    {
        PluralAttribute.CollectionType target;
        switch( source ) {
            case COLLECTION -> target = PluralAttribute.CollectionType.COLLECTION;
            case LIST -> target = PluralAttribute.CollectionType.LIST;
            case MAP -> target = PluralAttribute.CollectionType.MAP;
            case SET -> target = PluralAttribute.CollectionType.SET;
            default -> throw new IllegalArgumentException("Could not name Enum type " + source.name());
        }

        return target;
    }

    public static Type.PersistenceType convertPersistenceTypeEnum( javax.persistence.metamodel.Type.PersistenceType source )
    {
        Type.PersistenceType target;
        switch( source ) {
            case BASIC -> target = Type.PersistenceType.BASIC;
            case EMBEDDABLE -> target = Type.PersistenceType.EMBEDDABLE;
            case ENTITY -> target = Type.PersistenceType.ENTITY;
            case MAPPED_SUPERCLASS -> target = Type.PersistenceType.MAPPED_SUPERCLASS;
            default -> throw new IllegalArgumentException("Could not name Enum type " + source.name());
        }

        return target;
    }

    public static Bindable.BindableType convertBindableTypeEnum( javax.persistence.metamodel.Bindable.BindableType source )
    {
        Bindable.BindableType  target;
        switch( source ) {
            case ENTITY_TYPE -> target = Bindable.BindableType.ENTITY_TYPE;
            case PLURAL_ATTRIBUTE -> target = Bindable.BindableType.PLURAL_ATTRIBUTE;
            case SINGULAR_ATTRIBUTE -> target = Bindable.BindableType.SINGULAR_ATTRIBUTE;
            default -> throw new IllegalArgumentException("Could not name Enum type " + source.name());
        }

        return target;
    }

    public static javax.persistence.criteria.CriteriaBuilder.Trimspec convertTrimspecEnum( CriteriaBuilder.Trimspec source )
    {
        javax.persistence.criteria.CriteriaBuilder.Trimspec  target;

        switch( source ) {
            case BOTH -> target = javax.persistence.criteria.CriteriaBuilder.Trimspec.BOTH;
            case LEADING -> target = javax.persistence.criteria.CriteriaBuilder.Trimspec.LEADING;
            case TRAILING -> target = javax.persistence.criteria.CriteriaBuilder.Trimspec.TRAILING;
            default -> throw new IllegalArgumentException("Could not name Enum type " + source.name());
        }

        return target;
    }
}
