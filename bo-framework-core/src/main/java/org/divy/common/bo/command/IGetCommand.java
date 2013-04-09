package org.divy.common.bo.command;


public interface IGetCommand<ENTITY,ID>
{

	public abstract ENTITY get(ID identity);

}