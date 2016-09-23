package org.divy.common.bo.test;

import org.divy.common.bo.query.Query;
/**
 * 
 */

/**
 * @author Divyakumar
 *
 */
public interface ITestDataProvider<E> {

    /* Methods for Test Data generation */
    void modifyEntityWithTestData(E entity);

    void fillTestDataSet1(E entity);

    void fillTestDataSet2(E entity);

    E getEntityInstance();

    void initialize();

    Query createSearchQuery();
}
