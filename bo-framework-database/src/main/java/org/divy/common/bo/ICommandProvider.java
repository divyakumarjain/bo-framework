package org.divy.common.bo;

import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.command.IGetCommand;
import org.divy.common.bo.command.ISearchCommand;
import org.divy.common.bo.command.IUpdateCommand;

public interface ICommandProvider<ENTITY, ID>
{

	IGetCommand<ENTITY, ID> getGetCommand();

	ICreateCommand<ENTITY, ID> getCreateCommand();

	IDeleteCommand<ENTITY, ID> getDeleteCommand();
	
	IUpdateCommand<ENTITY, ID> getUpdateCommand();
	
	ISearchCommand<ENTITY, ID> getSearchCommand();
}