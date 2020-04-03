package org.sterzhen.rest.client;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class DeleteMethodDefinition extends MethodDefinition {

    protected DeleteMethodDefinition(String name, String path) {
        super(name, path);
    }

    @Override
    public HttpRequest buildHttpRequest(String address, String rootPath, Object[] args) {
        return HttpRequest.newBuilder()
                .uri(URI.create(address + "/" + rootPath + getPath(args)))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", consumesType)
                .DELETE()
                .build();
    }
}
