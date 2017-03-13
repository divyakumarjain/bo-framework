package org.divy.common.bo.command;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.context.CommandContext;
import org.divy.common.bo.query.Query;

import java.util.stream.Stream;


public abstract class AbstractDatabaseSearchCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I>
        implements ISearchCommand<E> {

    protected AbstractDatabaseSearchCommand(
            Class<E> typeParameterClass, CommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @Override
    public Stream<E> searchStream(Query query) {
        //TODO Use steam support when available in JPA
        return search(query)
                .stream();
    }
}
