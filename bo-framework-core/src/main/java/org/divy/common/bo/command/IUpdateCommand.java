package org.divy.common.bo.command;


public interface IUpdateCommand <ENTITY,ID>
{

	public abstract ENTITY update(ENTITY user);

}