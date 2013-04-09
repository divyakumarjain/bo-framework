package org.divy.common.bo.command;


public interface ICreateCommand <ENTITY,ID>
{

	public abstract ENTITY create(ENTITY entity);

}