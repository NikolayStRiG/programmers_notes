package org.sterzhen.rest.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class RestClientInvocationHandlerTest {

    public static final String MESSAGE = "test";
    public static final String JSON_MESSAGE = "{\"message\":\"test\"}";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ADDRESS_TEST = "http://localhost:8080";

    @Mock
    private HttpClient httpClient;
    @Mock
    private HttpResponse httpResponse;

    private TestInterface client;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        when(httpResponse.body()).thenReturn(JSON_MESSAGE);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);

        client = RestClientFactory.create(TestInterface.class, ADDRESS_TEST, httpClient);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void invokeSimpleTest() throws IOException, InterruptedException {

        final TestReturnType result = client.getTest();

        assertEquals(MESSAGE, result.getMessage());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/test", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
    }

    @Test
    public void invokeSimpleWithPathParamTest() throws IOException, InterruptedException {

        final TestReturnType result = client.getById(1L);

        assertEquals(MESSAGE, result.getMessage());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/1", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
    }

    @Test
    public void invokeSimpleWithPathParamTwoTest() throws IOException, InterruptedException {

        final TestReturnType result = client.getById(1L, "fieldName");

        assertEquals(MESSAGE, result.getMessage());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/1/field/fieldName", request.uri().toString());
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

        @GET
        @Path("{id}/field/{name}")
        @Produces(value={"application/json"})
        TestReturnType getById(@PathParam("id") Long id, @PathParam("name") String name);
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