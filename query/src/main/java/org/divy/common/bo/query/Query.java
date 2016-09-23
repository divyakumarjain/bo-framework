package org.divy.common.bo.query;

import java.util.Map;

public interface Query extends Map<String, Operator>
{
	Operator get(String attributeName);

	Operator put(String attributeName, Operator operator);

	Operator remove(String attributeName);
}
