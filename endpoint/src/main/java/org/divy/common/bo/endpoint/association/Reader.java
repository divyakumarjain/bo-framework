package org.divy.common.bo.endpoint.association;

import java.lang.reflect.InvocationTargetException;

public interface Reader {
    Object read(Object source, Object... argv) throws InvocationTargetException, IllegalAccessException;
}
