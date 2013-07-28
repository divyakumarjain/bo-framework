package org.divy.common.bo;

import java.util.List;

import org.divy.common.bo.query.IQuery;


public class AbstractBODatabaseRepository<ENTITY extends IBusinessObject<ID>, ID>
		implements IBORepository<ENTITY, ID>
{

	private ICommandProvider<ENTITY, ID> commandProvider;
	
	protected void setCommandProvider(ICommandProvider<ENTITY, ID> commandProvider)
	{
		this.commandProvider = commandProvider;
	}
	
	@Override
	public ENTITY create(ENTITY entity)
	{
		return commandProvider.getCreateCommand().create(entity);
	}

	@Override
	public ENTITY update(ENTITY entity)
	{
		return commandProvider.getUpdateCommand().update(entity);
	}


	@Override
	public List<ENTITY> search(IQuery query)
	{
		return commandProvider.getSearchCommand().search(query);
	}

	@Override
	public ENTITY get(ID identity)
	{
		return commandProvider.getGetCommand().get(identity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.IBOManager#deleteById(java.lang.Object)
	 */
	@Override
	public ENTITY deleteById(ID id) {
		return commandProvider.getDeleteCommand().deleteById(id);
	}
	
	@Override
	public ENTITY delete(ENTITY entity) {
		return commandProvider.getDeleteCommand().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.IBOManager#list()
	 */
	@Override
	public List<ENTITY> list() {
		return commandProvider.getSearchCommand().search(null);
	}
}
