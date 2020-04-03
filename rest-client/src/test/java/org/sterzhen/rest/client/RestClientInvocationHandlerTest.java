package org.sterzhen.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class RestClientInvocationHandlerTest {

    public static final String MESSAGE = "test";
    public static final String JSON_MESSAGE = "{\"id\":1,\"message\":\"test\"}";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ADDRESS_TEST = "http://localhost:8080";

    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private HttpClient httpClient;
    @Mock
    private HttpResponse httpResponse;

    private TestInterface client;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        client = RestClientFactory.create(TestInterface.class, ADDRESS_TEST, httpClient);
    }

    @AfterEach
    void tearDown() {
    }

    private void initSingle() throws IOException, InterruptedException {
        when(httpResponse.body()).thenReturn(JSON_MESSAGE);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
    }

    @Test
    public void invokeSimpleTest() throws IOException, InterruptedException {
        initSingle();

        final TestReturnType result = client.getTest();

        assertEquals(MESSAGE, result.getMessage());
        assertEquals(Long.valueOf(1), result.getId());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/test", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
        assertEquals("GET", request.method());
    }

    @Test
    public void invokeSimpleWithPathParamTest() throws IOException, InterruptedException {
        initSingle();

        final TestReturnType result = client.getById(1L);

        assertEquals(MESSAGE, result.getMessage());
        assertEquals(Long.valueOf(1), result.getId());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/1", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
        assertEquals("GET", request.method());
    }

    @Test
    public void invokeSimpleWithPathParamTwoTest() throws IOException, InterruptedException {
        initSingle();

        final TestReturnType result = client.getById(1L, "fieldName");

        assertEquals(MESSAGE, result.getMessage());
        assertEquals(Long.valueOf(1), result.getId());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/1/field/fieldName", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
        assertEquals("GET", request.method());
    }

    @Test
    public void invokePostTest() throws IOException, InterruptedException {
        initSingle();

        final var testCreate = new TestReturnType();
        testCreate.setMessage(MESSAGE);

        final TestReturnType result = client.create(testCreate);

        assertEquals(MESSAGE, result.getMessage());
        assertEquals(Long.valueOf(1), result.getId());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
        assertEquals("POST", request.method());

    }

    @Test
    public void invokePouTest() throws IOException, InterruptedException {
        initSingle();

        final var testCreate = new TestReturnType();
        testCreate.setId(1L);
        testCreate.setMessage(MESSAGE);

        final TestReturnType result = client.update(testCreate, 1L);

        assertEquals(MESSAGE, result.getMessage());
        assertEquals(Long.valueOf(1), result.getId());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources/1", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
        assertEquals("PUT", request.method());

    }

    @Test
    public void invokeGetListTest() throws IOException, InterruptedException {
        final var testCreate = new TestReturnType();
        testCreate.setId(1L);
        testCreate.setMessage(MESSAGE);


        when(httpResponse.body()).thenReturn(mapper.writeValueAsString(Collections.singleton(testCreate)));
        when(httpClient.send(any(), any())).thenReturn(httpResponse);

        final List<TestReturnType> result = client.getAll();

        assertEquals(1, result.size());
        assertEquals(MESSAGE, result.get(0).getMessage());
        assertEquals(Long.valueOf(1), result.get(0).getId());

        ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient, times(1)).send(captor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest request = captor.getValue();
        assertEquals("http://localhost:8080/api/info_resources", request.uri().toString());
        assertEquals(APPLICATION_JSON, request.headers().firstValue(CONTENT_TYPE).get());
        assertEquals("GET", request.method());

    }
}