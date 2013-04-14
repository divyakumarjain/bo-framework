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
	public abstract void modifyEntityWithTestData(ENTITY entity);

	public abstract void fillTestDataSet1(ENTITY entity);

	public abstract void fillTestDataSet2(ENTITY entity);

	public abstract ENTITY getEntityInstance();
}
