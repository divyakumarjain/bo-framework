package org.divy.common.bo.database.context;

public interface HierarchicalCommandContext extends CommandContext {
    CommandContext getParentContext();
    CommandContext createChildContext();
}
