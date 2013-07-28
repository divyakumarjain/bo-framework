package org.divy.common.bo.query;

import java.util.Map.Entry;
import java.util.Set;

public interface IQuery
{
	Object get(String attributeName);

	Object put(String attributeName, IOperator operator);

	Object remove(String attributeName);
	
	Set<Entry<String,IOperator>> getAll();
}
