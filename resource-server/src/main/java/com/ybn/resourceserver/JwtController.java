package com.ybn.resourceserver;

import com.ybn.common.dto.OAuth2TokenDto;
import com.ybn.common.dto.TicketAuthDto;
import com.ybn.resourceserver.service.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    @Autowired
    private JwtServiceImpl jwtService;
    @PostMapping("/generate")
    public ResponseEntity<OAuth2TokenDto> produce(@RequestBody  TicketAuthDto body) {
        return this.jwtService.produce(body.getUsername(), body.getPassword(), "/oauth/token");
    }

    @PostMapping("/refresh")
    public ResponseEntity<OAuth2TokenDto> refresh(@RequestParam("refresh_token") String refresh) {
        return this.jwtService.produce(refresh, "/oauth/token");
    }
}
