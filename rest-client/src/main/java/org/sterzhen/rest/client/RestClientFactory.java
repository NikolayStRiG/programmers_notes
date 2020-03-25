package org.sterzhen.rest.client;

import java.lang.reflect.Proxy;
import java.net.http.HttpClient;

public class RestClientFactory {

    private RestClientFactory() {
    }

    public static <T> T create(Class<T> restInterface, String address) {
        if (!restInterface.isInterface()) {
            throw new IllegalArgumentException("Not interface");
        }
        final Object proxy = Proxy.newProxyInstance(restInterface.getClassLoader(),
                new Class[]{restInterface}, RestClientInvocationHandler.create(restInterface, address));
        return restInterface.cast(proxy);
    }

    public static <T> T create(Class<T> restInterface, String address, HttpClient client) {
        if (!restInterface.isInterface()) {
            throw new IllegalArgumentException("Not interface");
        }
        final Object proxy = Proxy.newProxyInstance(restInterface.getClassLoader(),
                new Class[]{restInterface}, RestClientInvocationHandler.create(restInterface, address, client));
        return restInterface.cast(proxy);
    }
}
