package org.divy.common.bo.test;

import org.divy.common.bo.query.IQuery;
/**
 * 
 */

/**
 * @author Divyakumar
 *
 */
public interface ITestDataProvider<E, I> {

    /* Methods for Test Data generation */
    void modifyEntityWithTestData(E entity);

    void fillTestDataSet1(E entity);

    void fillTestDataSet2(E entity);

    E getEntityInstance();

    void initialize();

    IQuery createSearchQuery();
}
