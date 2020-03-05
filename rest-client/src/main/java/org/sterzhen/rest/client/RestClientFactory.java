package org.sterzhen.rest.client;

import java.lang.reflect.Proxy;
import java.net.http.HttpClient;

public class RestClientFactory {

    public static <T> T create(Class<T> restInterface, String address) {
        if (!restInterface.isInterface()) {
            throw new IllegalArgumentException("Not interface");
        }
        return (T) Proxy.newProxyInstance(restInterface.getClassLoader(),
                new Class[]{restInterface}, RestClientInvocationHandler.create(restInterface, address));
    }

    public static <T> T create(Class<T> restInterface, String address, HttpClient client) {
        if (!restInterface.isInterface()) {
            throw new IllegalArgumentException("Not interface");
        }
        return (T) Proxy.newProxyInstance(restInterface.getClassLoader(),
                new Class[]{restInterface}, RestClientInvocationHandler.create(restInterface, address, client));
    }
}
