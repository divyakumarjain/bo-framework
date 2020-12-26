package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.JavaXSelectionProxy;
import org.divy.common.bo.database.proxy.javax.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

class JakartaCriteriaBuilderProxyImpl implements CriteriaBuilder {
    private javax.persistence.criteria.CriteriaBuilder actual;

    public JakartaCriteriaBuilderProxyImpl( SessionFactoryImpl entityManagerFactory )
    {
        actual = new org.hibernate.query.criteria.internal.CriteriaBuilderImpl( entityManagerFactory );
    }

    @Override public CriteriaQuery<Object> createQuery()
    {
        return new JakartaCriteriaQueryProxy<>( actual.createQuery() );
    }

    @Override public <T> CriteriaQuery<T> createQuery( Class<T> resultClass )
    {
        return new JakartaCriteriaQueryProxy<>( actual.createQuery( resultClass ) );
    }

    @Override public CriteriaQuery<Tuple> createTupleQuery()
    {
        return new JakartaCriteriaQueryProxy<>( actual.createTupleQuery() );
    }

    @Override public <T> CriteriaUpdate<T> createCriteriaUpdate( Class<T> targetEntity )
    {
        return new JakartaCriteriaUpdateProxy<>( actual.createCriteriaUpdate( targetEntity ) );
    }

    @Override public <T> CriteriaDelete<T> createCriteriaDelete( Class<T> targetEntity )
    {
        return new JakartaCriteriaDeleteProxy<>( actual.createCriteriaDelete( targetEntity ) );
    }

    @Override public <Y> CompoundSelection<Y> construct( Class<Y> resultClass, Selection<?>... selections )
    {
        return new JakartaCompoundSelectionProxy<>( actual.construct( resultClass, ProxyUtil.convertArrayProxy( selections, JavaXSelectionProxy::new ) ) );
    }

    @Override public CompoundSelection<Tuple> tuple( Selection<?>... selections )
    {
        return new JakartaCompoundSelectionProxy<Tuple>( actual.tuple( ProxyUtil.convertArrayProxy( selections, JavaXSelectionProxy::new ) ) );
    }

    @Override public CompoundSelection<Object[]> array( Selection<?>... selections )
    {
        return new JakartaCompoundSelectionProxy<>( actual.array( ProxyUtil.convertArrayProxy( selections, JavaXSelectionProxy::new ) ) );
    }

    @Override public Order asc( Expression<?> x )
    {
        return new JakartaOrderProxy( actual.asc( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Order desc( Expression<?> x )
    {
        return new JakartaOrderProxy( actual.desc( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public <N extends Number> Expression<Double> avg( Expression<N> x )
    {
        return new JakartaExpressionProxy<>( actual.avg( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public <N extends Number> Expression<N> sum( Expression<N> x )
    {
        return new JakartaExpressionProxy<>( actual.sum( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<Long> sumAsLong( Expression<Integer> x )
    {
        return new JakartaExpressionProxy<>( actual.sumAsLong( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<Double> sumAsDouble( Expression<Float> x )
    {
        return new JakartaExpressionProxy<>( actual.sumAsDouble( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public <N extends Number> Expression<N> max( Expression<N> x )
    {
        return new JakartaExpressionProxy<>( actual.max( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public <N extends Number> Expression<N> min( Expression<N> x )
    {
        return new JakartaExpressionProxy<>( actual.min( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public <X extends Comparable<? super X>> Expression<X> greatest( Expression<X> x )
    {
        return new JakartaExpressionProxy<>( actual.greatest( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public <X extends Comparable<? super X>> Expression<X> least( Expression<X> x )
    {
        return new JakartaExpressionProxy<>( actual.least( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<Long> count( Expression<?> x )
    {
        return new JakartaExpressionProxy<>( actual.count( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<Long> countDistinct( Expression<?> x )
    {
        return new JakartaExpressionProxy<>( actual.countDistinct( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Predicate exists( Subquery<?> subquery )
    {
        return new JakartaPredicateProxy( actual.exists( new JavaxSubqueryProxy<>( subquery ) ) );
    }

    @Override public <Y> Expression<Y> all( Subquery<Y> subquery )
    {
        return new JakartaExpressionProxy<>( actual.all( new JavaxSubqueryProxy<>( subquery ) ) );
    }

    @Override public <Y> Expression<Y> some( Subquery<Y> subquery )
    {
        return new JakartaExpressionProxy<>( actual.some( new JavaxSubqueryProxy<>( subquery ) ) );
    }

    @Override public <Y> Expression<Y> any( Subquery<Y> subquery )
    {
        return new JakartaExpressionProxy<>( actual.any( new JavaxSubqueryProxy<>( subquery ) ) );
    }

    @Override public Predicate and( Expression<Boolean> x, Expression<Boolean> y )
    {
        return new JakartaPredicateProxy( actual.and( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Predicate and( Predicate... restrictions )
    {
        return new JakartaPredicateProxy( actual.and( ProxyUtil.convertArrayProxy( restrictions, JavaXPredicateProxy::new ) ) );
    }

    @Override public Predicate or( Expression<Boolean> x, Expression<Boolean> y )
    {
        return new JakartaPredicateProxy( actual.or( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Predicate or( Predicate... restrictions )
    {
        return new JakartaPredicateProxy( actual.or( ProxyUtil.convertArrayProxy( restrictions, JavaXPredicateProxy::new ) ) );
    }

    @Override public Predicate not( Expression<Boolean> restriction )
    {
        return new JakartaPredicateProxy( actual.not( new JavaXExpressionProxy<>( restriction ) ) );
    }

    @Override public Predicate conjunction()
    {
        return new JakartaPredicateProxy( actual.conjunction() );
    }

    @Override public Predicate disjunction()
    {
        return new JakartaPredicateProxy( actual.disjunction() );
    }

    @Override public Predicate isTrue( Expression<Boolean> x )
    {
        return new JakartaPredicateProxy( actual.isTrue( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Predicate isFalse( Expression<Boolean> x )
    {
        return new JakartaPredicateProxy( actual.isFalse( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Predicate isNull( Expression<?> x )
    {
        return new JakartaPredicateProxy( actual.isNull( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Predicate isNotNull( Expression<?> x )
    {
        return new JakartaPredicateProxy( actual.isNotNull( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Predicate equal( Expression<?> x, Expression<?> y )
    {
        return new JakartaPredicateProxy( actual.equal( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Predicate equal( Expression<?> x, Object y )
    {
        return new JakartaPredicateProxy( actual.equal( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public Predicate notEqual( Expression<?> x, Expression<?> y )
    {
        return new JakartaPredicateProxy( actual.notEqual( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Predicate notEqual( Expression<?> x, Object y )
    {
        return new JakartaPredicateProxy( actual.notEqual( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate greaterThan( Expression<? extends Y> x, Expression<? extends Y> y )
    {
        return new JakartaPredicateProxy( actual.greaterThan( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate greaterThan( Expression<? extends Y> x, Y y )
    {
        return new JakartaPredicateProxy( actual.greaterThan( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo( Expression<? extends Y> x, Expression<? extends Y> y )
    {
        return new JakartaPredicateProxy( actual.greaterThanOrEqualTo( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo( Expression<? extends Y> x, Y y )
    {
        return new JakartaPredicateProxy( actual.greaterThanOrEqualTo( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate lessThan( Expression<? extends Y> x, Expression<? extends Y> y )
    {
        return new JakartaPredicateProxy( actual.lessThan( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate lessThan( Expression<? extends Y> x, Y y )
    {
        return new JakartaPredicateProxy( actual.lessThan( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo( Expression<? extends Y> x, Expression<? extends Y> y )
    {
        return new JakartaPredicateProxy( actual.lessThanOrEqualTo( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo( Expression<? extends Y> x, Y y )
    {
        return new JakartaPredicateProxy( actual.lessThanOrEqualTo( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate between( Expression<? extends Y> v, Expression<? extends Y> x, Expression<? extends Y> y )
    {
        return new JakartaPredicateProxy( actual.between( new JavaXExpressionProxy<>( v ), new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <Y extends Comparable<? super Y>> Predicate between( Expression<? extends Y> var1, Y var2, Y var3 )
    {
        return new JakartaPredicateProxy( actual.between( new JavaXExpressionProxy<>( var1 ), var2, var3 ) );
    }

    @Override public Predicate gt( Expression<? extends Number> x, Expression<? extends Number> y )
    {
        return new JakartaPredicateProxy( actual.gt( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Predicate gt( Expression<? extends Number> x, Number y )
    {
        return new JakartaPredicateProxy( actual.gt( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public Predicate ge( Expression<? extends Number> x, Expression<? extends Number> y )
    {
        return new JakartaPredicateProxy( actual.ge( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Predicate ge( Expression<? extends Number> x, Number y )
    {
        return new JakartaPredicateProxy( actual.ge( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public Predicate lt( Expression<? extends Number> x, Expression<? extends Number> y )
    {
        return new JakartaPredicateProxy( actual.lt( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Predicate lt( Expression<? extends Number> x, Number y )
    {
        return new JakartaPredicateProxy( actual.lt( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public Predicate le( Expression<? extends Number> x, Expression<? extends Number> y )
    {
        return new JakartaPredicateProxy( actual.le( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Predicate le( Expression<? extends Number> x, Number y )
    {
        return new JakartaPredicateProxy( actual.le( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <N extends Number> Expression<N> neg( Expression<N> x )
    {
        return new JakartaExpressionProxy<>( actual.neg( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public <N extends Number> Expression<N> abs( Expression<N> x )
    {
        return new JakartaExpressionProxy<>( actual.abs( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public <N extends Number> Expression<N> sum( Expression<? extends N> x, Expression<? extends N> y )
    {
        return new JakartaExpressionProxy<>( actual.sum( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <N extends Number> Expression<N> sum( Expression<? extends N> x, N y )
    {
        return new JakartaExpressionProxy<>( actual.sum( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <N extends Number> Expression<N> sum( N x, Expression<? extends N> y )
    {
        return new JakartaExpressionProxy<>( actual.sum( x, new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <N extends Number> Expression<N> prod( Expression<? extends N> x, Expression<? extends N> y )
    {
        return new JakartaExpressionProxy<>( actual.prod( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <N extends Number> Expression<N> prod( Expression<? extends N> x, N y )
    {
        return new JakartaExpressionProxy<>( actual.prod( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <N extends Number> Expression<N> prod( N x, Expression<? extends N> y )
    {
        return new JakartaExpressionProxy<>( actual.prod( x, new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <N extends Number> Expression<N> diff( Expression<? extends N> x, Expression<? extends N> y )
    {
        return new JakartaExpressionProxy<>( actual.diff( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <N extends Number> Expression<N> diff( Expression<? extends N> x, N y )
    {
        return new JakartaExpressionProxy<>( actual.diff( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <N extends Number> Expression<N> diff( N x, Expression<? extends N> y )
    {
        return new JakartaExpressionProxy<>( actual.diff( x, new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Expression<Number> quot( Expression<? extends Number> x, Expression<? extends Number> y )
    {
        return new JakartaExpressionProxy<>( actual.quot( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Expression<Number> quot( Expression<? extends Number> x, Number y )
    {
        return new JakartaExpressionProxy<>( actual.quot( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public Expression<Number> quot( Number x, Expression<? extends Number> y )
    {
        return new JakartaExpressionProxy<>( actual.quot( x, new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Expression<Integer> mod( Expression<Integer> x, Expression<Integer> y )
    {
        return new JakartaExpressionProxy<>( actual.mod( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Expression<Integer> mod( Expression<Integer> x, Integer y )
    {
        return new JakartaExpressionProxy<>( actual.mod( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public Expression<Integer> mod( Integer x, Expression<Integer> y )
    {
        return new JakartaExpressionProxy<>( actual.mod( x, new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Expression<Double> sqrt( Expression<? extends Number> x )
    {
        return new JakartaExpressionProxy<>( actual.sqrt( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<Long> toLong( Expression<? extends Number> number )
    {
        return new JakartaExpressionProxy<>( actual.toLong( new JavaXExpressionProxy<>( number ) ) );
    }

    @Override public Expression<Integer> toInteger( Expression<? extends Number> number )
    {
        return new JakartaExpressionProxy<>( actual.toInteger( new JavaXExpressionProxy<>( number ) ) );
    }

    @Override public Expression<Float> toFloat( Expression<? extends Number> number )
    {
        return new JakartaExpressionProxy<>( actual.toFloat( new JavaXExpressionProxy<>( number ) ) );
    }

    @Override public Expression<Double> toDouble( Expression<? extends Number> number )
    {
        return new JakartaExpressionProxy<>( actual.toDouble( new JavaXExpressionProxy<>( number ) ) );
    }

    @Override public Expression<BigDecimal> toBigDecimal( Expression<? extends Number> number )
    {
        return new JakartaExpressionProxy<>( actual.toBigDecimal( new JavaXExpressionProxy<>( number ) ) );
    }

    @Override public Expression<BigInteger> toBigInteger( Expression<? extends Number> number )
    {
        return new JakartaExpressionProxy<>( actual.toBigInteger( new JavaXExpressionProxy<>( number ) ) );
    }

    @Override public Expression<String> toString( Expression<Character> character )
    {
        return new JakartaExpressionProxy<>( actual.toString( new JavaXExpressionProxy<>( character ) ) );
    }

    @Override public <T> Expression<T> literal( T value )
    {
        return new JakartaExpressionProxy<>( actual.literal( value ) );
    }

    @Override public <T> Expression<T> nullLiteral( Class<T> resultClass )
    {
        return new JakartaExpressionProxy<>( actual.nullLiteral( resultClass ) );
    }

    @Override public <T> ParameterExpression<T> parameter( Class<T> paramClass )
    {
        return new JakartaParameterExpressionProxy<>( actual.parameter( paramClass ) );
    }

    @Override public <T> ParameterExpression<T> parameter( Class<T> paramClass, String name )
    {
        return new JakartaParameterExpressionProxy<>( actual.parameter( paramClass, name ) );
    }

    @Override public <C extends Collection<?>> Predicate isEmpty( Expression<C> collection )
    {
        return new JakartaPredicateProxy( actual.isEmpty( new JavaXExpressionProxy<>( collection ) ) );
    }

    @Override public <C extends Collection<?>> Predicate isNotEmpty( Expression<C> collection )
    {
        return new JakartaPredicateProxy( actual.isNotEmpty( new JavaXExpressionProxy<>( collection ) ) );
    }

    @Override public <C extends Collection<?>> Expression<Integer> size( Expression<C> collection )
    {
        return new JakartaExpressionProxy<>( actual.size( new JavaXExpressionProxy<>( collection ) ) );
    }

    @Override public <C extends Collection<?>> Expression<Integer> size( C collection )
    {
        return new JakartaExpressionProxy<>( actual.size( collection ) );
    }

    @Override public <E, C extends Collection<E>> Predicate isMember( Expression<E> elem, Expression<C> collection )
    {
        return new JakartaPredicateProxy( actual.isMember( new JavaXExpressionProxy<>( elem ), new JavaXExpressionProxy<>( collection ) ) );
    }

    @Override public <E, C extends Collection<E>> Predicate isMember( E elem, Expression<C> collection )
    {
        return new JakartaPredicateProxy( actual.isMember( elem, new JavaXExpressionProxy<>( collection ) ) );
    }

    @Override public <E, C extends Collection<E>> Predicate isNotMember( Expression<E> elem, Expression<C> collection )
    {
        return new JakartaPredicateProxy( actual.isNotMember( new JavaXExpressionProxy<>( elem ), new JavaXExpressionProxy<>( collection ) ) );
    }

    @Override public <E, C extends Collection<E>> Predicate isNotMember( E elem, Expression<C> collection )
    {
        return new JakartaPredicateProxy( actual.isNotMember( elem, new JavaXExpressionProxy<>( collection ) ) );
    }

    @Override public <V, M extends Map<?, V>> Expression<Collection<V>> values( M map )
    {
        return new JakartaExpressionProxy<>( actual.values( map ) );
    }

    @Override public <K, M extends Map<K, ?>> Expression<Set<K>> keys( M map )
    {
        return new JakartaExpressionProxy<>( actual.keys( map ) );
    }

    @Override public Predicate like( Expression<String> x, Expression<String> pattern )
    {
        return new JakartaPredicateProxy( actual.like( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( pattern ) ) );
    }

    @Override public Predicate like( Expression<String> x, String pattern )
    {
        return new JakartaPredicateProxy( actual.like( new JavaXExpressionProxy<>( x ), pattern ) );
    }

    @Override public Predicate like( Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar )
    {
        return new JakartaPredicateProxy(
              actual.like( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( pattern ), new JavaXExpressionProxy<>( escapeChar ) ) );
    }

    @Override public Predicate like( Expression<String> x, Expression<String> pattern, char escapeChar )
    {
        return new JakartaPredicateProxy( actual.like( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( pattern ), escapeChar ) );
    }

    @Override public Predicate like( Expression<String> x, String pattern, Expression<Character> escapeChar )
    {
        return new JakartaPredicateProxy( actual.like( new JavaXExpressionProxy<>( x ), pattern, new JavaXExpressionProxy<>( escapeChar ) ) );
    }

    @Override public Predicate like( Expression<String> x, String pattern, char escapeChar )
    {
        return new JakartaPredicateProxy( actual.like( new JavaXExpressionProxy<>( x ), pattern, escapeChar ) );
    }

    @Override public Predicate notLike( Expression<String> x, Expression<String> pattern )
    {
        return new JakartaPredicateProxy( actual.notLike( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( pattern ) ) );
    }

    @Override public Predicate notLike( Expression<String> x, String pattern )
    {
        return new JakartaPredicateProxy( actual.notLike( new JavaXExpressionProxy<>( x ), pattern ) );
    }

    @Override public Predicate notLike( Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar )
    {
        return new JakartaPredicateProxy(
              actual.notLike( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( pattern ), new JavaXExpressionProxy<>( escapeChar ) ) );
    }

    @Override public Predicate notLike( Expression<String> x, Expression<String> pattern, char escapeChar )
    {
        return new JakartaPredicateProxy( actual.notLike( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( pattern ), escapeChar ) );
    }

    @Override public Predicate notLike( Expression<String> x, String pattern, Expression<Character> escapeChar )
    {
        return new JakartaPredicateProxy( actual.notLike( new JavaXExpressionProxy<>( x ), pattern, new JavaXExpressionProxy<>( escapeChar ) ) );
    }

    @Override public Predicate notLike( Expression<String> x, String pattern, char escapeChar )
    {
        return new JakartaPredicateProxy( actual.notLike( new JavaXExpressionProxy<>( x ), pattern, escapeChar ) );
    }

    @Override public Expression<String> concat( Expression<String> x, Expression<String> y )
    {
        return new JakartaExpressionProxy<>( actual.concat( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Expression<String> concat( Expression<String> x, String y )
    {
        return new JakartaExpressionProxy<>( actual.concat( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public Expression<String> concat( String x, Expression<String> y )
    {
        return new JakartaExpressionProxy<>( actual.concat( x, new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public Expression<String> substring( Expression<String> x, Expression<Integer> from )
    {
        return new JakartaExpressionProxy<>( actual.substring( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( from ) ) );
    }

    @Override public Expression<String> substring( Expression<String> x, int from )
    {
        return new JakartaExpressionProxy<>( actual.substring( new JavaXExpressionProxy<>( x ), from ) );
    }

    @Override public Expression<String> substring( Expression<String> x, Expression<Integer> from, Expression<Integer> len )
    {
        return new JakartaExpressionProxy<>(
              actual.substring( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( from ), new JavaXExpressionProxy<>( len ) ) );
    }

    @Override public Expression<String> substring( Expression<String> x, int from, int len )
    {
        return new JakartaExpressionProxy<>( actual.substring( new JavaXExpressionProxy<>( x ), from, len ) );
    }

    @Override public Expression<String> trim( Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.trim( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<String> trim( Trimspec ts, Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.trim( ProxyUtil.convertTrimspecEnum(ts), new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<String> trim( Expression<Character> t, Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.trim( new JavaXExpressionProxy<>( t ), new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<String> trim( Trimspec ts, Expression<Character> t, Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.trim( ProxyUtil.convertTrimspecEnum( ts ), new JavaXExpressionProxy<>( t ), new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<String> trim( char t, Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.trim( t, new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<String> trim( Trimspec ts, char t, Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.trim( ProxyUtil.convertTrimspecEnum( ts ), t, new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<String> lower( Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.lower( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<String> upper( Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.upper( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<Integer> length( Expression<String> x )
    {
        return new JakartaExpressionProxy<>( actual.length( new JavaXExpressionProxy<>( x ) ) );
    }

    @Override public Expression<Integer> locate( Expression<String> x, Expression<String> pattern )
    {
        return new JakartaExpressionProxy<>( actual.locate( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( pattern ) ) );
    }

    @Override public Expression<Integer> locate( Expression<String> x, String pattern )
    {
        return new JakartaExpressionProxy<>( actual.locate( new JavaXExpressionProxy<>( x ), pattern ) );
    }

    @Override public Expression<Integer> locate( Expression<String> x, Expression<String> pattern, Expression<Integer> from )
    {
        return new JakartaExpressionProxy<>(
              actual.locate( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( pattern ), new JavaXExpressionProxy<>( from ) ) );
    }

    @Override public Expression<Integer> locate( Expression<String> x, String pattern, int from )
    {
        return new JakartaExpressionProxy<>( actual.locate( new JavaXExpressionProxy<>( x ), pattern, from ) );
    }

    @Override public Expression<Date> currentDate()
    {
        return new JakartaExpressionProxy<>( actual.currentDate() );
    }

    @Override public Expression<Timestamp> currentTimestamp()
    {
        return new JakartaExpressionProxy<>( actual.currentTimestamp() );
    }

    @Override public Expression<Time> currentTime()
    {
        return new JakartaExpressionProxy<>( actual.currentTime() );
    }

    @Override public <T> In<T> in( Expression<? extends T> expression )
    {
        return new JakartaInProxy<>( actual.in( new JavaXExpressionProxy<>( expression ) ) );
    }

    @Override public <Y> Expression<Y> coalesce( Expression<? extends Y> x, Expression<? extends Y> y )
    {
        return new JakartaExpressionProxy<>( actual.coalesce( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <Y> Expression<Y> coalesce( Expression<? extends Y> x, Y y )
    {
        return new JakartaExpressionProxy<>( actual.coalesce( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <Y> Expression<Y> nullif( Expression<Y> x, Expression<?> y )
    {
        return new JakartaExpressionProxy<>( actual.nullif( new JavaXExpressionProxy<>( x ), new JavaXExpressionProxy<>( y ) ) );
    }

    @Override public <Y> Expression<Y> nullif( Expression<Y> x, Y y )
    {
        return new JakartaExpressionProxy<>( actual.nullif( new JavaXExpressionProxy<>( x ), y ) );
    }

    @Override public <T> Coalesce<T> coalesce()
    {
        return new JakartaCoalesceProxy<>( actual.coalesce() );
    }

    @Override public <C, R> SimpleCase<C, R> selectCase( Expression<? extends C> expression )
    {
        return new JakartaSimpleCaseProxy<>( actual.selectCase( new JavaXExpressionProxy<>( expression ) ) );
    }

    @Override public <R> Case<R> selectCase()
    {
        return new JakartaCaseProxy<>( actual.selectCase() );
    }

    @Override public <T> Expression<T> function( String name, Class<T> type, Expression<?>... args )
    {
        return new JakartaExpressionProxy<>( actual.function( name, type, ProxyUtil.convertArrayProxy( args, JavaXExpressionProxy::new ) ) );
    }

    @Override public <X, T, V extends T> Join<X, V> treat( Join<X, T> join, Class<V> type )
    {
        return new JakartaJoinProxy<>( actual.treat( new JavaXJoinProxy<>( join ), type ) );
    }

    @Override public <X, T, E extends T> CollectionJoin<X, E> treat( CollectionJoin<X, T> join, Class<E> type )
    {
        return new JakartaCollectionJoinProxy<>( actual.treat( new JavaXCollectionJoinProxy<>( join ), type ) );
    }

    @Override public <X, T, E extends T> SetJoin<X, E> treat( SetJoin<X, T> join, Class<E> type )
    {
        return new JakartaSetJoinProxy<>( actual.treat( new JavaXSetJoinProxy<>( join ), type ) );
    }

    @Override public <X, T, E extends T> ListJoin<X, E> treat( ListJoin<X, T> join, Class<E> type )
    {
        return new JakartaListJoinProxy<>( actual.treat( new JavaXListJoinProxy<>( join ), type ) );
    }

    @Override public <X, K, T, V extends T> MapJoin<X, K, V> treat( MapJoin<X, K, T> join, Class<V> type )
    {
        return new JakartaMapJoinProxy<>( actual.treat( new JavaXMapJoinProxy<>( join ), type ) );
    }

    @Override public <X, T extends X> Path<T> treat( Path<X> path, Class<T> type )
    {
        return new JakartaPathProxy<>( actual.treat( new JavaXPathProxy<>( path ), type ) );
    }

    @Override public <X, T extends X> Root<T> treat( Root<X> root, Class<T> type )
    {
        return new JakartaRootProxy<>( actual.treat( new JavaXRootProxy<>( root ), type ) );
    }
}
