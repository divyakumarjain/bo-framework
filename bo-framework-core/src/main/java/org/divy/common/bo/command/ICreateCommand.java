package org.divy.common.bo.command;


public interface ICreateCommand <ENTITY,ID>
{

	abstract ENTITY create(ENTITY entity);

}