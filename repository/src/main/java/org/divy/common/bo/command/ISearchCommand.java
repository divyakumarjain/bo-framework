package org.divy.common.bo.command;

import org.divy.common.bo.query.Query;

import java.util.List;
import java.util.stream.Stream;

/**
 *
 *
 */
public interface ISearchCommand<E, I> extends ICommand {
    List<E> search(Query query);
    Stream<E> searchStream(Query query);
}
