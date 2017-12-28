package org.divy.common.bo.endpoint.hypermedia.association.reader;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

@Ignore
//TODO Fix this failing unit test
//FIXME Fix this failing unit test
public class TestDynamicReader {

    @Test
    public void testWithMethodOnAndRead() {

        MockReturnType mockReturnType = Mockito.mock(MockReturnType.class);
        MockInterface mockInterface = Mockito.mock(MockInterface.class);

        Mockito.doReturn(mockReturnType).when(mockInterface).mockMethod1();

        DynamicReader reader = new DynamicReader();
        reader.withMethodOn(MockInterface.class).mockMethod1().mockMethod2();

        reader.read(mockInterface);
        Mockito.verify(mockInterface, Mockito.times(1)).mockMethod1();
        Mockito.verify(mockReturnType, Mockito.times(1)).mockMethod2();
    }
}
