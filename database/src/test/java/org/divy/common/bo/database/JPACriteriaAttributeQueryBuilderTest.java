package org.divy.common.bo.database;

import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.query.AttributeQuery;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.query.operator.And;
import org.divy.common.bo.query.operator.Not;
import org.divy.common.bo.query.operator.comparison.impl.*;
import org.divy.common.bo.query.operator.impl.AndImpl;
import org.divy.common.bo.query.operator.impl.NotImpl;
import org.divy.common.bo.query.operator.impl.OrImpl;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.path.SingularAttributePath;
import org.hibernate.jpa.criteria.predicate.ComparisonPredicate;
import org.hibernate.jpa.criteria.predicate.CompoundPredicate;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;


import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
public class JPACriteriaAttributeQueryBuilderTest {

    JPACriteriaQueryBuilder criteriaQueryBuilder;

    @Before
    public void setupCriteriaQueryBuilder() {
        EntityManager mockEntityManager = mock(EntityManager.class);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.divy.mock");
        CriteriaBuilder mockCriteriaBuilder = new CriteriaBuilderImpl((EntityManagerFactoryImpl)entityManagerFactory);
        doReturn(mockCriteriaBuilder).when(mockEntityManager).getCriteriaBuilder();

        criteriaQueryBuilder = new JPACriteriaQueryBuilder(mockEntityManager, MockEntity.class);
    }

    @Test
    public void equalsQuery() throws Exception {

        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", new EqualsComparisonImpl("1"));

        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);

        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.EQUAL
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    @Test
    public void notQuery() throws Exception {
        AttributeQuery query = new AttributeQuery();
        Not operator = new NotImpl( new GreaterThanComparisonImpl(1));
        query.put("integerAttribute", operator);
        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);
        assertCriteriaQueryForComparisonPredicateWithNot(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.GREATER_THAN
                , "integerAttribute");
    }

    private void assertCriteriaQueryForComparisonPredicateWithNot(CriteriaQuery criteriaQuery, Enum operator, String lhs) {
        Predicate actualPredicate = criteriaQuery.getRestriction();
        Assert.assertThat(actualPredicate, is(instanceOf(CompoundPredicate.class)));
        Assert.assertThat(actualPredicate.getExpressions(), contains(hasProperty("negated", is(true))));
    }

    @Test
    public void gtQuery() throws Exception {

        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", new GreaterThanComparisonImpl<Number>(1));

        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);

        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.GREATER_THAN
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    @Test
    public void geQuery() throws Exception {

        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", new GreaterThanEqualToComparisonImpl<Number>( 1));

        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);

        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.GREATER_THAN_OR_EQUAL
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    @Test
    public void ltQuery() throws Exception {

        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", new LessThanComparisonImpl<>( 1));

        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);

        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.LESS_THAN
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    @Test
    public void leQuery() throws Exception {
        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", new LessThanEqualToComparisonImpl<>( 1));
        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);
        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.LESS_THAN_OR_EQUAL
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    private void assertCriteriaQueryForComparisonPredicate(CriteriaQuery criteriaQuery, Enum operator, String lhs,PredicateAssert<ComparisonPredicate> predicateAssert) {
        assertCriteriaQueryForComparisonPredicate(criteriaQuery, operator, lhs);
        Predicate actualPredicate = criteriaQuery.getRestriction();
        ComparisonPredicate comparisonPredicate = (ComparisonPredicate) actualPredicate.getExpressions().get(0);
        predicateAssert.assertPredicate(comparisonPredicate, operator, lhs);
    }

    private void assertCriteriaQueryForComparisonPredicate(CriteriaQuery criteriaQuery, Enum operator, String lhs) {
        Predicate actualPredicate = criteriaQuery.getRestriction();
        Assert.assertThat(actualPredicate, is(instanceOf(CompoundPredicate.class)));
        Assert.assertThat(actualPredicate.getExpressions(), contains(is(instanceOf(ComparisonPredicate.class))));
    }

    private void assertComparisonPredicate(ComparisonPredicate comparisonPredicate, Enum operator, String lhs) {
        Assert.assertThat(comparisonPredicate.getComparisonOperator(), is(operator));
        Assert.assertThat(((SingularAttributePath)comparisonPredicate.getLeftHandOperand()).getAttribute().getName(), is(lhs));
    }

    @Test
    public void orQuery() throws Exception {
        AttributeQuery query = new AttributeQuery();
        OrImpl operator = new OrImpl( new EqualsComparisonImpl("1"), new LessThanComparisonImpl<>( 1));
        query.put("integerAttribute", operator);
        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);
        assertCriteriaQueryForCompoundPredicate(criteriaQuery
                , ComparisonPredicate.BooleanOperator.OR
                , "integerAttribute");
    }

    @Test
    public void andQuery() throws Exception {
        AttributeQuery query = new AttributeQuery();
        And operator = new AndImpl( new EqualsComparisonImpl("1"), new LessThanComparisonImpl<>( 1));
        query.put("integerAttribute", operator);
        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);
        assertCriteriaQueryForCompoundPredicate(criteriaQuery
                , ComparisonPredicate.BooleanOperator.AND
                , "integerAttribute");
    }

    private void assertCriteriaQueryForCompoundPredicate(CriteriaQuery criteriaQuery, Enum operator, String lhs) {
        Predicate actualPredicate = criteriaQuery.getRestriction();
        Assert.assertThat(actualPredicate, is(instanceOf(CompoundPredicate.class)));
        Assert.assertThat(actualPredicate.getExpressions(), contains(is(instanceOf(CompoundPredicate.class))));
        CompoundPredicate compoundPredicate = (CompoundPredicate) actualPredicate.getExpressions().get(0);
        Assert.assertThat(compoundPredicate.getOperator(), is(operator));
    }

    @FunctionalInterface
    interface PredicateAssert<T> {
        void assertPredicate(T comparisonPredicate, Enum operator, String lhs);
    }
}