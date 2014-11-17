/**
 * 
 */
package org.divy.common.bo.database.mock;

import org.divy.common.bo.command.db.AbstractDatabaseSearchCommand;
import org.divy.common.bo.command.db.IDBCommandContext;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class MockSearchCommand extends
        AbstractDatabaseSearchCommand<MockEntity, String> {

    /**
     * @param entityClass
     * @param context
     */
    public MockSearchCommand(IDBCommandContext context) {
        super(MockEntity.class, context);
    }
}
