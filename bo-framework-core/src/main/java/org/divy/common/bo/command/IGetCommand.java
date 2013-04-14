package org.divy.common.bo.command;


public interface IGetCommand<ENTITY,ID>
{
	ENTITY get(ID identity);

}