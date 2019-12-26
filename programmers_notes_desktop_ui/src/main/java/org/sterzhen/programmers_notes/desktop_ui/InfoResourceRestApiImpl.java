package org.sterzhen.programmers_notes.desktop_ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.sterzhen.programmers_notes.desktop_ui.dto.InfoResourceDto;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;
import org.sterzhen.programmers_notes.rest_api.service_interface.InfoResourceServiceApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class InfoResourceRestApiImpl implements InfoResourceServiceApi {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public InfoResourceApi getById(Long id) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(20))
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/info_resource/2"))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().isEmpty() ? null : objectMapper.readValue(response.body(), InfoResourceDto.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
