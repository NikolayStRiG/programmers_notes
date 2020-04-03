package org.sterzhen.rest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ProcessingException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class PutMethodDefinition extends MethodDefinition {

    private final ObjectMapper objectMapper;

    protected PutMethodDefinition(String name, String path, ObjectMapper objectMapper) {
        super(name, path);
        this.objectMapper = objectMapper;
    }

    @Override
    public HttpRequest buildHttpRequest(String address, String rootPath, Object[] args) {
        try {
            return HttpRequest.newBuilder()
                    .uri(URI.create(address + "/" + rootPath + getPath(args)))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", consumesType)
                    .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(args[0])))
                    .build();
        } catch (JsonProcessingException e) {
            throw new ProcessingException(e);
        }
    }
}
