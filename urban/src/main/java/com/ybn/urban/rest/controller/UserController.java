package com.ybn.urban.rest.controller;

import com.ybn.common.dto.UserDto;
import com.ybn.urban.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDto body) {
        return ResponseEntity.ok(this.userService.create(body));
    }
}
