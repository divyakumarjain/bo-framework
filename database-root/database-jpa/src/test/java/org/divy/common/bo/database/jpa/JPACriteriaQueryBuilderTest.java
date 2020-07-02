package org.divy.common.bo.database.jpa;

import org.divy.common.bo.query.AttributeQuery;
import org.divy.common.bo.query.operator.And;
import org.divy.common.bo.query.operator.Not;
import static org.divy.common.bo.query.operator.comparison.OperatorFactory.*;

import org.divy.common.bo.query.operator.Operator;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.path.SingularAttributePath;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate;
import org.hibernate.query.criteria.internal.predicate.CompoundPredicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;

public class JPACriteriaQueryBuilderTest
{

    JPACriteriaQueryBuilder criteriaQueryBuilder;

    @Before
    public void setupCriteriaQueryBuilder() {
        EntityManager mockEntityManager = Mockito.mock(EntityManager.class);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.divy.mock");
        CriteriaBuilder mockCriteriaBuilder = new CriteriaBuilderImpl((SessionFactoryImpl) entityManagerFactory);
        doReturn(mockCriteriaBuilder).when(mockEntityManager).getCriteriaBuilder();

        criteriaQueryBuilder = new JPACriteriaQueryBuilder(mockEntityManager, MockEntity.class);
    }

    @Test
    public void equalsQuery() {

        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", equalsComparison("1"));

        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);

        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.EQUAL
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    @Test
    public void notQuery() {
        AttributeQuery query = new AttributeQuery();
        Not operator = not( greaterThanComparison(1));
        query.put("integerAttribute", operator);
        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);
        assertCriteriaQueryForComparisonPredicateWithNot(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.GREATER_THAN
                , "integerAttribute");
    }

    private void assertCriteriaQueryForComparisonPredicateWithNot(CriteriaQuery criteriaQuery, Enum operator, String lhs) {
        Predicate actualPredicate = criteriaQuery.getRestriction();
        assertThat(actualPredicate, Matchers.is(Matchers.instanceOf(CompoundPredicate.class)));
        assertThat(actualPredicate.getExpressions(), IsIterableContainingInOrder.contains(Matchers.hasProperty("negated", Matchers.is(true))));
    }

    @Test
    public void gtQuery() {

        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", greaterThanComparison(1));

        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);

        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.GREATER_THAN
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    @Test
    public void geQuery() {

        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", greaterThanEqualToComparison(1));

        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);

        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.GREATER_THAN_OR_EQUAL
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    @Test
    public void ltQuery() {

        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", lessThanComparison(1));

        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);

        assertCriteriaQueryForComparisonPredicate(criteriaQuery
                , ComparisonPredicate.ComparisonOperator.LESS_THAN
                , "integerAttribute"
                , this::assertComparisonPredicate);
    }

    @Test
    public void leQuery() {
        AttributeQuery query = new AttributeQuery();
        query.put("integerAttribute", lessThanEqualToComparison(1));
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
        assertThat(actualPredicate, Matchers.is(Matchers.instanceOf(CompoundPredicate.class)));
        assertThat(actualPredicate.getExpressions(), IsIterableContainingInOrder.contains(Matchers.is(Matchers.instanceOf(ComparisonPredicate.class))));
    }

    private void assertComparisonPredicate(ComparisonPredicate comparisonPredicate, Enum operator, String lhs) {
        assertThat(comparisonPredicate.getComparisonOperator(), Matchers.is(operator));
        assertThat(((SingularAttributePath)comparisonPredicate.getLeftHandOperand()).getAttribute().getName(), Matchers.is(lhs));
    }

    @Test
    public void orQuery() {
        AttributeQuery query = new AttributeQuery();
        Operator operator = or( equalsComparison("1"), lessThanComparison(1));
        query.put("integerAttribute", operator);
        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);
        assertCriteriaQueryForCompoundPredicate(criteriaQuery
                , ComparisonPredicate.BooleanOperator.OR
                , "integerAttribute");
    }

    @Test
    public void andQuery() {
        AttributeQuery query = new AttributeQuery();
        And operator = and( equalsComparison("1"), lessThanComparison( 1));
        query.put("integerAttribute", operator);
        CriteriaQuery criteriaQuery = criteriaQueryBuilder.createCriteriaQuery(query);
        assertCriteriaQueryForCompoundPredicate(criteriaQuery
                , ComparisonPredicate.BooleanOperator.AND
                , "integerAttribute");
    }

    private void assertCriteriaQueryForCompoundPredicate(CriteriaQuery criteriaQuery, Enum operator, String lhs) {
        Predicate actualPredicate = criteriaQuery.getRestriction();
        assertThat(actualPredicate, Matchers.is(Matchers.instanceOf(CompoundPredicate.class)));
        assertThat(actualPredicate.getExpressions(), IsIterableContainingInOrder.contains(Matchers.is(Matchers.instanceOf(CompoundPredicate.class))));
        CompoundPredicate compoundPredicate = (CompoundPredicate) actualPredicate.getExpressions().get(0);
        assertThat(compoundPredicate.getOperator(), Matchers.is(operator));
    }

    @FunctionalInterface
    interface PredicateAssert<T> {
        void assertPredicate(T comparisonPredicate, Enum operator, String lhs);
    }
}
