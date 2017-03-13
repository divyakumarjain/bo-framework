package org.divy.common.bo.search;

import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.query.KeywordQuery;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.query.operator.And;
import org.divy.common.bo.query.operator.Not;
import org.divy.common.bo.query.operator.Operator;
import org.divy.common.bo.query.operator.Or;
import org.divy.common.bo.query.operator.comparison.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class HibernateSearchCommand<E, I> implements ISearchCommand<E> {

    private HibernateSearchCommandContext context;

    private final Class<? extends E> entityType;
    private FullTextEntityManager fullTextEntityManager;

    public HibernateSearchCommand(Class<? extends E> typeParameterClass) {
        this.entityType = typeParameterClass;
    }

    protected void setContext(HibernateSearchCommandContext context)
    {
        this.context = context;
    }


    private Class<? extends E> getEntityType() {
        return entityType;
    }

    @Override
    public List<E> search(Query query) {

        if (query instanceof KeywordQuery) {
            KeywordQuery keywordQuery = (KeywordQuery) query;

            try(FullTextSession fullTextSession = getFullTextEntityManager()) {
                Transaction tx = fullTextSession.beginTransaction();
                javax.persistence.Query jpaQuery = buildQuery(keywordQuery);
                List resultList = jpaQuery.getResultList();
                tx.commit();
                return resultList;
            }
        } else {
            throw new IllegalArgumentException("Only Keyword Queries are supported");
        }
    }

    private Session searchSession() {
        return getSessionFactory().getCurrentSession();
    }

    private SessionFactory getSessionFactory() {
        return new Configuration().buildSessionFactory();
    }

    private javax.persistence.Query buildQuery(KeywordQuery keywordQuery) {

        QueryBuilder qb = getQueryBuilder();

        BooleanJunction booleanJunction = qb.bool();

        for(Map.Entry<String, Operator> keywordEntry : keywordQuery.entrySet()) {
            booleanJunction = buildMustJunction(booleanJunction, keywordEntry.getKey(), keywordEntry.getValue());
        }

        org.apache.lucene.search.Query luceneQuery = booleanJunction
                .createQuery();

        return fullTextEntityManager.createFullTextQuery(luceneQuery, getEntityType());
    }

    private BooleanJunction buildMustJunction(BooleanJunction booleanJunction, String key, Operator operator) {
        if(operator instanceof Not){
            Not notOperator = (Not) operator;
            return booleanJunction.must(buildQuery(key, notOperator.getOperation())).not();
        } else {
            return booleanJunction.must(buildQuery(key, operator));
        }
    }

    private BooleanJunction buildShouldJunction(BooleanJunction booleanJunction, String key, Operator operator) {
        if(operator instanceof Not){
            Not notOperator = (Not) operator;
            return booleanJunction.must(buildQuery(key, notOperator.getOperation())).not();
        } else {
            return booleanJunction.should(buildQuery(key, operator));
        }
    }

    private QueryBuilder getQueryBuilder() {
        return getFullTextEntityManager().getSearchFactory()
                    .buildQueryBuilder().forEntity(getEntityType()).get();
    }

    private org.apache.lucene.search.Query buildQuery(String key, Operator operator) {
        if (operator instanceof EqualsComparison<?>) {
            EqualsComparison<String> eqOperator = (EqualsComparison<String>) operator;
            return getQueryBuilder().keyword().onField(key).matching(eqOperator.getValue()).createQuery();
        } else if (operator instanceof GreaterThanComparison) {
            GreaterThanComparison<Number> gtOperator = (GreaterThanComparison<Number>) operator;
            return getQueryBuilder().range().onField(key).above(gtOperator.getValue()).excludeLimit().createQuery();
        } else if (operator instanceof GreaterThanEqualToComparison) {
            GreaterThanEqualToComparison<Number> gteOperator = (GreaterThanEqualToComparison<Number>) operator;
            return getQueryBuilder().range().onField(key).above(gteOperator.getValue()).createQuery();
        } else if (operator instanceof LessThanComparison) {
            LessThanComparison<Number> ltOperator = (LessThanComparison<Number>) operator;
            return getQueryBuilder().range().onField(key).below(ltOperator.getValue()).excludeLimit().createQuery();
        } else if (operator instanceof LessThanEqualToComparison) {
            LessThanEqualToComparison<Number> leOperator = (LessThanEqualToComparison<Number>) operator;
            return getQueryBuilder().range().onField(key).below(leOperator.getValue()).createQuery();
        } else if (operator instanceof Or) {
            Or orOperator = (Or) operator;
            BooleanJunction booleanJunction = getQueryBuilder().bool();

            for (Operator orOperatorChild : orOperator.getOperations()) {
                booleanJunction = buildShouldJunction(booleanJunction, key, orOperatorChild);
            }
            return booleanJunction.createQuery();
        } else if (operator instanceof And) {
            And andOperator = (And) operator;
            BooleanJunction booleanJunction = getQueryBuilder().bool();

            for (Operator orOperatorChild : andOperator.getOperations()) {
                booleanJunction = buildMustJunction(booleanJunction, key, orOperatorChild);
            }
            return booleanJunction.createQuery();
        } else {
            throw new IllegalArgumentException("Does not support Query of type " + operator.getClass().getSimpleName());
        }
    }

    private FullTextSession getFullTextEntityManager() {
        return Search.getFullTextSession(searchSession());
    }

    @Override
    public Stream<E> searchStream(Query query) {
        //TODO Use steam support when available
        return search(query)
                .stream();
    }
}
