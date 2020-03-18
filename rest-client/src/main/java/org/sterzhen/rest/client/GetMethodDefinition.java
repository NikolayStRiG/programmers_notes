package org.sterzhen.rest.client;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class GetMethodDefinition extends MethodDefinition {

    public GetMethodDefinition(String name, String path) {
        super(name, path);
    }

    @Override
    public HttpRequest buildHttpRequest(final String address, final String rootPath, final Object[] args) {
        return HttpRequest.newBuilder()
                .uri(URI.create(address + "/" + rootPath + getPath(args)))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", consumesType)
                .GET()
                .build();
    }
}
