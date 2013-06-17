/**
 * 
 */
package org.divy.common.bo.command;

import java.util.List;

import org.divy.common.bo.IQuery;

/**
 * @author Divyakumar
 *
 */
public interface ISearchCommand<ENTITY, ID> {
	List<ENTITY> search(IQuery<ENTITY> query);
}
