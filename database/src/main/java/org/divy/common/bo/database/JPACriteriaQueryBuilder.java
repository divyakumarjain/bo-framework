package org.divy.common.bo.database;

import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.query.operator.*;
import org.divy.common.bo.query.operator.comparison.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JPACriteriaQueryBuilder<E> {
    private final EntityManager entityManager;
    private final Class<? extends E> entityType;


    public JPACriteriaQueryBuilder(EntityManager entityManager, Class<? extends E> entityType) {
        this.entityManager = entityManager;
        this.entityType = entityType;
    }

    public CriteriaQuery<? extends E> createCriteriaQuery(IQuery query) {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<? extends E> criteriaQuery = criteriaBuilder.createQuery(entityType);

        Root<? extends E> entityRoot = criteriaQuery.from(entityType);

        if (query != null && !query.isEmpty()) {

            List<Predicate> predicates = new ArrayList<>();

            for (Map.Entry<String, Operator> entry : query.entrySet()) {
                predicates.add(createPredicate(entry,criteriaBuilder,entityRoot));
            }
            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

            return criteriaQuery;
        }
        return criteriaQuery;
    }

    private Predicate createPredicate(Map.Entry<String, Operator> operatorEntry,
                                      CriteriaBuilder cb,Root<? extends E> root ) {

        Operator operatorEntryValue = operatorEntry.getValue();
        Path<Number> path = root.get(operatorEntry.getKey());
        return createPredicates(path, cb, operatorEntryValue);
    }

    private Predicate createPredicates(Path<Number> path, CriteriaBuilder cb, Operator operatorEntryValue) {
        Predicate returnPredicate = null;
        if (operatorEntryValue instanceof EqualsComparison<?>) {
            EqualsComparison<String> eqOperator = (EqualsComparison<String>) operatorEntryValue;
            returnPredicate = cb.equal(path,eqOperator.getValue());
        } else if (operatorEntryValue instanceof GreaterThanComparison) {
            GreaterThanComparison<Number> gtOperator = (GreaterThanComparison<Number>) operatorEntryValue;
            returnPredicate = cb.gt(path, createNumber(gtOperator));
        } else if (operatorEntryValue instanceof GreaterThanEqualToComparison) {
            GreaterThanEqualToComparison<Number> gteOperator = (GreaterThanEqualToComparison<Number>) operatorEntryValue;
            returnPredicate = cb.ge(path, createNumber(gteOperator));
        } else if (operatorEntryValue instanceof LessThanComparison) {
            LessThanComparison<Number> ltOperator = (LessThanComparison<Number>) operatorEntryValue;
            returnPredicate = cb.lt(path, createNumber(ltOperator));
        } else if (operatorEntryValue instanceof LessThanEqualToComparison) {
            LessThanEqualToComparison<Number> leOperator = (LessThanEqualToComparison<Number>) operatorEntryValue;
            returnPredicate = cb.le(path, createNumber(leOperator));
        } else if (operatorEntryValue instanceof InComparison) {
            InComparison<String> inOperator = (InComparison<String>) operatorEntryValue;
            returnPredicate = cb.in(path).in(inOperator.getOperations());
        } else if (operatorEntryValue instanceof Or) {
            Or orOperator = (Or) operatorEntryValue;
            returnPredicate = cb.or(createPredicates(path,cb,orOperator.getOperations()));
        } else if (operatorEntryValue instanceof And) {
            And andOperator = (And) operatorEntryValue;
            returnPredicate = cb.and(createPredicates(path,cb,andOperator.getOperations()));
        } else if (operatorEntryValue instanceof Not) {
            Not notOperator = (Not) operatorEntryValue;
            returnPredicate = cb.not(createPredicates(path,cb,notOperator.getOperation()));
        }
        return returnPredicate;
    }

    private Predicate[] createPredicates(Path<Number> path, CriteriaBuilder cb, List<Operator> operators) {
        return operators.stream().map( operator -> createPredicates(path,cb,operator)).collect(Collectors.toSet()).toArray(new Predicate[operators.size()]);

    }

    private Number createNumber(Comparison<Number> gtOperator) {
        //TODO If string parse the string and convert it to number
        return gtOperator.getValue();
    }
}
