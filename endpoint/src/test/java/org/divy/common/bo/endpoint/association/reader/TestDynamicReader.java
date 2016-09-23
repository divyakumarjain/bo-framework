package org.divy.common.bo.endpoint.association.reader;

import org.divy.common.bo.endpoint.association.reader.mock.MockInterface;
import org.divy.common.bo.endpoint.association.reader.mock.MockReturnType;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.*;

public class TestDynamicReader {

    @Test
    public void testWithMethodOnAndRead() throws InvocationTargetException, IllegalAccessException {

        MockReturnType mockReturnType = mock(MockReturnType.class);
        MockInterface mockInterface = mock(MockInterface.class);

        doReturn(mockReturnType).when(mockInterface).mockMethod1();

        DynamicReader reader = new DynamicReader();
        reader.withMethodOn(MockInterface.class).mockMethod1().mockMethod2();

        reader.read(mockInterface);
        verify(mockInterface,times(1)).mockMethod1();
        verify(mockReturnType,times(1)).mockMethod2();
    }
}
