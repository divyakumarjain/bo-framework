package org.divy.common.bo.context;

public interface ICommandContext
{
	ICommandContext getParentContext();
	
	ICommandContext createChildContext();
	
	void commit();

	void begin();
}
