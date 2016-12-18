package org.divy.common.bo.query;

import org.divy.common.bo.query.operator.Operator;

import java.util.Map;

public interface IQuery extends Map<String, Operator>
{
	Operator get(String attributeName);

	Operator put(String attributeName, Operator operator);

	Operator remove(String attributeName);
}
