package org.divy.common.bo;

import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.command.IGetCommand;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.command.IUpdateCommand;

public interface ICommandProvider<ENTITY, ID>
{

	public IGetCommand<ENTITY, ID> getGetCommand();

	public ICreateCommand<ENTITY, ID> getCreateCommand();

	public IDeleteCommand<ENTITY, ID> getDeleteCommand();
	
	public IUpdateCommand<ENTITY, ID> getUpdateCommand();
	
	public ISearchCommand<ENTITY, ID> getSearchCommand();
}