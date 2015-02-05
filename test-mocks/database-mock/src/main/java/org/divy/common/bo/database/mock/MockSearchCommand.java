/**
 * 
 */
package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.AbstractDatabaseSearchCommand;
import org.divy.common.bo.IDBCommandContext;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class MockSearchCommand extends
        AbstractDatabaseSearchCommand<MockEntity, UUID> {

    /**
     * @param entityClass
     * @param context
     */
    public MockSearchCommand(IDBCommandContext context) {
        super(MockEntity.class, context);
    }
}
