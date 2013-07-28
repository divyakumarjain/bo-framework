package org.divy.common.bo.command;


public interface IUpdateCommand <ENTITY,ID> extends ICommand
{
	ENTITY update(ENTITY user);

}