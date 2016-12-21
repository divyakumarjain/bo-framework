/**
 * 
 */
package org.divy.common.bo.command;

import org.divy.common.bo.query.Query;

import java.util.List;

import org.divy.common.bo.query.Query;

/**
 * @author Divyakumar
 *
 */
public interface ISearchCommand<E, I> extends ICommand {
    List<E> search(Query query);
}
