package org.divy.common.bo.database;

import org.divy.common.bo.query.Query;

import java.util.List;
import java.util.stream.Stream;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;

public class AbstractBODatabaseRepository<E extends IBusinessObject<I>, I>
        implements IBORepository<E, I>
{

    private ICommandProvider<E, I> commandProvider;

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
    public E update(I id, E entity)
    {
        return commandProvider.getUpdateCommand().update(id, entity);
    }


    @Override
    public List<E> search(Query query)
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

    @Override
    public List<E> list() {
        return commandProvider
                .getSearchCommand()
                .search(null);
    }

    @Override
    public Stream<E> stream() {
        return commandProvider
                .getSearchCommand()
                .searchStream(null);
    }

    @Override
    public Stream<E> searchStream(Query businessObjectQuery) {
        return commandProvider
                .getSearchCommand()
                .searchStream(businessObjectQuery);
    }
}
