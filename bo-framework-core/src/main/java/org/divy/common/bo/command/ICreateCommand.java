package org.divy.common.bo.command;


public interface ICreateCommand <ENTITY,ID> extends ICommand
{
	ENTITY create(ENTITY entity);
}