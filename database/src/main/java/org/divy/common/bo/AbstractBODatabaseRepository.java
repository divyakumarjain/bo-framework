package org.divy.common.bo;

import org.divy.common.bo.query.IQuery;

import javax.inject.Inject;
import java.util.List;


public class AbstractBODatabaseRepository<E extends IBusinessObject<I>, I>
        implements IBORepository<E, I>
{

    private ICommandProvider<E, I> commandProvider;

    @Inject
    public AbstractBODatabaseRepository(ICommandProvider<E, I> commandProvider) {
        this.commandProvider = commandProvider;
    }

    protected void setCommandProvider(ICommandProvider<E, I> commandProvider)
    {
        this.commandProvider = commandProvider;
    }

    @Override
    public E create(E entity)
    {
        return commandProvider.getCreateCommand().create(entity);
    }

    @Override
    public E update(E entity)
    {
        return commandProvider.getUpdateCommand().update(entity);
    }


    @Override
    public List<E> search(IQuery query)
    {
        return commandProvider.getSearchCommand().search(query);
    }

    @Override
    public E get(I identity)
    {
        return commandProvider.getGetCommand().get(identity);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.IBOManager#deleteById(java.lang.Object)
     */
    @Override
    public E deleteById(I id) {
        return commandProvider.getDeleteCommand().deleteById(id);
    }

    @Override
    public E delete(E entity) {
        return commandProvider.getDeleteCommand().delete(entity);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.IBOManager#list()
     */
    @Override
    public List<E> list() {
        return commandProvider.getSearchCommand().search(null);
    }
}
