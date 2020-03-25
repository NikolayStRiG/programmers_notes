package org.sterzhen.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.Path;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RestClientInvocationHandler<T> implements InvocationHandler {

    private final Class<T> restInterfaceClass;
    private String rootPath;
    private final Map<Method, MethodDefinition> methodDefinitionMap = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient client;
    private final String address;

    public static <T> RestClientInvocationHandler<T> create(Class<T> restInterfaceClass, String address) {
        return create(restInterfaceClass, address, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build());
    }

    public static <T> RestClientInvocationHandler<T> create(Class<T> restInterfaceClass, String address, HttpClient client) {
        if (!restInterfaceClass.isInterface()) {
            throw new IllegalArgumentException("Not interface");
        }
        final RestClientInvocationHandler<T> instant = new RestClientInvocationHandler<>(restInterfaceClass, address, client);
        instant.init(new MethodDefinitionFactoryImpl());
        return instant;
    }

    private RestClientInvocationHandler(Class<T> restInterfaceClass, String address, HttpClient client) {
        this.restInterfaceClass = restInterfaceClass;
        this.address = address;
        this.client = client;
    }

    private void init(MethodDefinitionFactory methodDefinitionFactory) {
        final Path path = restInterfaceClass.getAnnotation(Path.class);
        if (path == null) {
            throw new IllegalArgumentException("Not Path annotation");
        }
        this.rootPath = path.value();

        for (Method m : restInterfaceClass.getMethods()) {
            final MethodDefinition definition = methodDefinitionFactory.create(m);
            methodDefinitionMap.put(m, definition);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final MethodDefinition definition = methodDefinitionMap.get(method);
        final HttpRequest request = definition.buildHttpRequest(address, rootPath, args);
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().isEmpty() ? null : objectMapper.readValue(response.body(), definition.getOutParam());
    }

}
