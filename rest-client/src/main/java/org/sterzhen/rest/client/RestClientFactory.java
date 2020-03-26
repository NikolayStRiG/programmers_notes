package org.sterzhen.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Proxy;
import java.net.http.HttpClient;
import java.time.Duration;

public class RestClientFactory {

    private RestClientFactory() {
    }

    public static <T> T create(Class<T> restInterface, String address) {
        return create(restInterface, address, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build(), new ObjectMapper());
    }

    public static <T> T create(Class<T> restInterface, String address, HttpClient client) {
        return create(restInterface, address, client, new ObjectMapper());
    }

    public static <T> T create(Class<T> restInterface, String address, ObjectMapper objectMapper) {
        return create(restInterface, address, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build(), objectMapper);
    }

    public static <T> T create(Class<T> restInterface, String address, HttpClient client, ObjectMapper objectMapper) {
        final Object proxy = Proxy.newProxyInstance(restInterface.getClassLoader(),
                new Class[]{restInterface}, RestClientInvocationHandler.create(restInterface, address, client, objectMapper));
        return restInterface.cast(proxy);
    }
}
