package org.divy.common.bo.test;
/**
 * 
 */

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public interface ITestDataProvider<ENTITY, ID> {

	/* Methods for Test Data generation */
	void modifyEntityWithTestData(ENTITY entity);

	void fillTestDataSet1(ENTITY entity);

	void fillTestDataSet2(ENTITY entity);

	ENTITY getEntityInstance();
}
