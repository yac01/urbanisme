package com.ybn.tickets.rest.controller;

import com.ybn.tickets.rest.dto.OAuth2TokenDto;
import com.ybn.tickets.rest.dto.TicketAuthDto;
import com.ybn.tickets.service.impl.auth.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController("/jwt")
public class JwtController {
    @Autowired
    private JwtServiceImpl jwtService;
    private static final String OAUTH2_GENERATE_ENDPOINT = "/oauth/token";

    @PostMapping("/generate")
    public ResponseEntity<OAuth2TokenDto> generate(@RequestBody TicketAuthDto body) {
        return this.jwtService.produce(body.getUsername(), body.getPassword(), "password", OAUTH2_GENERATE_ENDPOINT);
    }
}
