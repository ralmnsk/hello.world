package com.hello.world.hello.world.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.world.hello.world.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class UserService {
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private ObjectMapper objectMapper;

    public List<User> getUsers() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://jsonplaceholder.typicode.com/posts"))
//                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        try {
            String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
            return objectMapper.readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        } catch (InterruptedException | ExecutionException | TimeoutException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
