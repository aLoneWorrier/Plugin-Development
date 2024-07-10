package com.example.plugin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestApiService {

    private final RestTemplate restTemplate;

    public RestApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String consumeApi(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
