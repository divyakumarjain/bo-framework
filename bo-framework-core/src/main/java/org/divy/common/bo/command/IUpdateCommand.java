package org.divy.common.bo.command;


public interface IUpdateCommand <ENTITY,ID>
{
	ENTITY update(ENTITY user);

}