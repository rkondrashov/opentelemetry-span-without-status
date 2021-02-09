package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExampleController {

    private final RestTemplate restTemplate;

    public ExampleController(RestTemplateBuilder restTemplateBuilder, @Value("${httpbin.uri}") String uri) {
        restTemplate = restTemplateBuilder
                .rootUri(uri)
                .errorHandler(new DefaultResponseErrorHandler() {
                    @Override
                    public boolean hasError(HttpStatus statusCode) {
                        return false;
                    }
                })
                .build();
    }

    @GetMapping("/{status}")
    public ResponseEntity<String> get(@PathVariable("status") String status) {
        return restTemplate.exchange("/" + status, HttpMethod.GET, null, String.class);
    }
}
