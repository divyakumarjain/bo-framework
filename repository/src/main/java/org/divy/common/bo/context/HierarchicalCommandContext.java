package org.divy.common.bo.context;

public interface HierarchicalCommandContext extends CommandContext {
    CommandContext getParentContext();
    CommandContext createChildContext();
}
