package org.divy.common.bo.test;

import org.divy.common.bo.query.IQuery;
/**
 * 
 */

/**
 * @author Divyakumar
 *
 */
public interface ITestDataProvider<ENTITY, ID> {

    /* Methods for Test Data generation */
    void modifyEntityWithTestData(ENTITY entity);

    void fillTestDataSet1(ENTITY entity);

    void fillTestDataSet2(ENTITY entity);

    ENTITY getEntityInstance();

    void initialize();

    IQuery createSearchQuery();
}
