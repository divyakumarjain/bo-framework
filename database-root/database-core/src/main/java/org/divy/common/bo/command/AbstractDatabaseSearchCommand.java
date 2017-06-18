package org.divy.common.bo.command;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.context.CommandContext;
import org.divy.common.bo.query.Query;

import java.util.stream.Stream;


public abstract class AbstractDatabaseSearchCommand<E extends BusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I>
        implements SearchCommand<E> {

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
