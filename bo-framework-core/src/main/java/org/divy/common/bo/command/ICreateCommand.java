package org.divy.common.bo.command;


public interface ICreateCommand <ENTITY,ID>
{
	ENTITY create(ENTITY entity);
}