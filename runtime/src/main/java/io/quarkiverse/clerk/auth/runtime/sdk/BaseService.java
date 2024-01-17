package io.quarkiverse.clerk.auth.runtime.sdk;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseService {

    private final Client client;
    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public BaseService(Client client) {
        this.client = client;
        this.baseUrl = client.getBaseUrl();
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public HttpRequest newRequest(String method, String url, Object body) throws JsonProcessingException {
        String fullUrl = URI.create(baseUrl).resolve(url).toString();

        System.out.println("fullUrl: " + fullUrl);

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .method(method, HttpRequest.BodyPublishers.noBody());

        if (body != null) {
            String jsonBody = objectMapper.writeValueAsString(body);
            requestBuilder = requestBuilder
                    .header("Content-Type", "application/json")
                    .method(method, HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8));
        }

        requestBuilder.header("X-Clerk-SDK", System.getProperty("java.version"));
        requestBuilder.header("Authorization", "Bearer " + client.getToken());

        return requestBuilder.build();
    }

    public Client getClient() {
        return client;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
