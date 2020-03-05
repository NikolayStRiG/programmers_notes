package org.sterzhen.rest.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestClientInvocationHandlerTest {

    public static final String MESSAGE = "test";
    public static final String JSON_MESSAGE = "{\"message\":\"test\"}";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ADDRESS_TEST = "http://localhost:8080";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void invokeSimpleTest() throws IOException, InterruptedException {
        final HttpClient httpClient = Mockito.mock(HttpClient.class);
        final HttpResponse httpResponse = Mockito.mock(HttpResponse.class);

        when(httpResponse.body()).thenReturn(JSON_MESSAGE);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);

        final TestInterface client = RestClientFactory.create(TestInterface.class, ADDRESS_TEST, httpClient);

        final TestReturnType result = client.getTest();

        assertEquals(MESSAGE, result.getMessage());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/test", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
    }

    @Test
    void invokeSimpleWithPathParamTest() throws IOException, InterruptedException {
        final HttpClient httpClient = Mockito.mock(HttpClient.class);
        final HttpResponse httpResponse = Mockito.mock(HttpResponse.class);

        when(httpResponse.body()).thenReturn(JSON_MESSAGE);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);

        final TestInterface client = RestClientFactory.create(TestInterface.class, ADDRESS_TEST, httpClient);

        final TestReturnType result = client.getById(1L);

        assertEquals(MESSAGE, result.getMessage());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/1", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
    }

    @Path("api/info_resources")
    public interface TestInterface {

        @GET
        @Path("test")
        @Produces(value={"application/json"})
        TestReturnType getTest();

        @GET
        @Path("{id}")
        @Produces(value={"application/json"})
        TestReturnType getById(@PathParam("id") Long id);
    }

    public static class TestReturnType{

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}