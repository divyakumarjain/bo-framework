/**
 * 
 */
package org.divy.common.bo.command;

import java.util.List;

import org.divy.common.bo.query.IQuery;

/**
 * @author Divyakumar
 *
 */
public interface ISearchCommand<ENTITY, ID> extends ICommand {
    List<ENTITY> search(IQuery query);
}
