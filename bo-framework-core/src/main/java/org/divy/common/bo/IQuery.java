package org.divy.common.bo;

public interface IQuery<TYPE>
{
	Object get(String attributeName);

	Object put(String attributeName, Object attributeValue);

	Object remove(String attributeName);
}
