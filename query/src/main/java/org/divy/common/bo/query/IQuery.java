package org.divy.common.bo.query;

import java.util.Map;

public interface IQuery extends Map<String, IOperator>
{
	IOperator get(String attributeName);

	IOperator put(String attributeName, IOperator operator);

	IOperator remove(String attributeName);
}
