package org.divy.common.bo.rest;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class LinkBuilderFactoryImplTest {

    private LinkBuilderFactoryImpl underTest;

    @Test
    public void newBuilderWithoutRequest() throws Exception {

        underTest = new LinkBuilderFactoryImpl();
        String result = underTest.newBuilder().build();

        assertThat(result, notNullValue());


    }

    @Test
    public void newBuilderWithRequestProtoHeader() throws Exception {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        doReturn("https").when(mockRequest).getHeader("X-Orig-Proto");
        underTest = new LinkBuilderFactoryImpl(mockRequest);

        String result = underTest.newBuilder().build();

        assertThat(result, notNullValue());
        assertThat(result, is("https://localhost:8080"));
    }

    @Test
    public void newBuilderWithRequestBaseHeader() throws Exception {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        doReturn("api").when(mockRequest).getHeader("X-Orig-Base");
        underTest = new LinkBuilderFactoryImpl(mockRequest);

        String result = underTest.newBuilder().build();

        assertThat(result, notNullValue());
        assertThat(result, is("http://localhost:8080/api"));
    }

    @Test
    public void newBuilderWithUriInfo() throws Exception {

        UriInfo uriInfo = mock(UriInfo.class);
        doReturn(new URI("http://urihost/api/resource")).when(uriInfo).getAbsolutePath();
        doReturn(new URI("http://urihost/api")).when(uriInfo).getBaseUri();
        doReturn("/api").when(uriInfo).getPath();
        underTest = new LinkBuilderFactoryImpl();

        String result = underTest.newBuilder(uriInfo).build();

        assertThat(result, notNullValue());
        assertThat(result, is("http://urihost/api"));
    }

    @Test
    public void getOriginalHost() throws Exception {
    }

}