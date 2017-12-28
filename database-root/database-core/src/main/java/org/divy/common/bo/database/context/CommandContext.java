package org.divy.common.bo.database.context;

public interface CommandContext
{
    void commit();
    void begin();
    void rollBack();
}
