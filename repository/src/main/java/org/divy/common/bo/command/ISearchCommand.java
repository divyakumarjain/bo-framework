/**
 * 
 */
package org.divy.common.bo.command;

import org.divy.common.bo.query.IQuery;

import java.util.List;

/**
 * @author Divyakumar
 *
 */
public interface ISearchCommand<E, I> extends ICommand {
    List<E> search(IQuery query);
}
