package org.divy.common.bo.command;


public interface IDeleteCommand <ENTITY,ID>
{
	ENTITY deleteById(ID id);
	ENTITY delete(ENTITY entity);
}
