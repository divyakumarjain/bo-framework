package org.divy.common.bo.context;

public interface CommandContext
{
    void commit();
    void begin();
    void rollBack();
}
