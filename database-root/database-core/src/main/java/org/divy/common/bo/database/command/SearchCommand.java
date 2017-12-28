package org.divy.common.bo.database.command;

import org.divy.common.bo.query.Query;

import java.util.List;
import java.util.stream.Stream;

public interface SearchCommand<E> extends Command {
    List<E> search(Query query);
    Stream<E> searchStream(Query query);
}
