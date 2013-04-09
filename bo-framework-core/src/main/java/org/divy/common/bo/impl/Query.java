package org.divy.common.bo.impl;

import java.util.HashMap;

import org.divy.common.bo.IQuery;

public class Query<TYPE> extends HashMap<String, Object> implements
		IQuery<TYPE>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6670043546158499600L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.IQuery#get(java.lang.String)
	 */
	@Override
	public Object get(String attributeName) {
		return super.get(attributeName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.IQuery#remove(java.lang.String)
	 */
	@Override
	public Object remove(String attributeName) {
		return super.remove(attributeName);
	}

}
