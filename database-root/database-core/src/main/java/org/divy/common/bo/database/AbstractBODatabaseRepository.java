package org.divy.common.bo.database;

import org.divy.common.bo.repository.BORepository;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.database.command.CommandProvider;
import org.divy.common.bo.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class AbstractBODatabaseRepository<E extends BusinessObject<I>, I>
        implements BORepository<E, I>
{

    private CommandProvider<E, I> commandProvider;

    public AbstractBODatabaseRepository(CommandProvider<E, I> commandProvider) {
        this.commandProvider = commandProvider;
    }

    protected void setCommandProvider(CommandProvider<E, I> commandProvider)
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
    public Optional<E> get(I identity)
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
