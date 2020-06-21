package com.ybn.tickets.rest.controller;

import com.ybn.tickets.rest.dto.UserDto;
import com.ybn.tickets.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto body) {
        return ResponseEntity.ok(this.userService.create(body));
    }
}
