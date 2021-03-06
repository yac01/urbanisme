package com.ybn.resourceserver.service;

import com.ybn.common.dto.OAuth2TokenDto;
import com.ybn.resourceserver.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtServiceImpl {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppConfig config;

    public ResponseEntity<OAuth2TokenDto> produce(String username, String password, String endpoint) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        body.add("password", password);
        body.add("grant_type", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(this.config.getClientId(), this.config.getClientSecret());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(body, headers);
        String url = new StringBuilder(config.getRunningOn()).append(endpoint).toString();
        return this.restTemplate.postForEntity(url, entity, OAuth2TokenDto.class);
    }

    public ResponseEntity<OAuth2TokenDto> produce(String token, String endpoint) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("refresh_token", token);
        body.add("grant_type", "refresh_token");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(this.config.getClientId(), this.config.getClientSecret());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(body, headers);
        String url = new StringBuilder(config.getRunningOn()).append(endpoint).toString();
        return this.restTemplate.postForEntity(url, entity, OAuth2TokenDto.class);
    }
}
