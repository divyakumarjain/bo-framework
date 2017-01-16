/**
 * 
 */
package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.database.AbstractDatabaseSearchCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

/**
 *.a.jain@hp.com
 *
 */
public class MockSearchCommand extends
        AbstractDatabaseSearchCommand<MockEntity, UUID> {

    /**
     * @param context
     */
    public MockSearchCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }
}
