package org.divy.common.bo.dynamic.clazz.member.method.reader;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class DynamicReaderTest
{

    @Test
    public void testWithMethodOnAndRead() {

        MockReturnType mockReturnType = mock(MockReturnType.class);
        MockInterface mockInterface = mock(MockInterface.class);

        doReturn(mockReturnType).when(mockInterface).mockMethod1();

        DynamicReader reader = new DynamicReader();
        reader.withMethodOn(MockInterface.class)
              .mockMethod1()
              .mockMethod2();

        reader.read(mockInterface);
        verify(mockInterface, times(1)).mockMethod1();
        verify(mockReturnType, times(1)).mockMethod2();
    }
}
