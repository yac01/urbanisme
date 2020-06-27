package com.ybn.urban.rest.controller;

import com.ybn.common.collection.TicketUser;
import com.ybn.common.dto.UserDto;
import com.ybn.common.repository.AuthorityRepository;
import com.ybn.common.repository.UserRepository;
import com.ybn.urban.rest.technical.RestPage;
import com.ybn.urban.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/urban/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDto body) {
        return ResponseEntity.ok(this.userService.create(body));
    }

    @GetMapping("/list")
    public RestPage<TicketUser> list(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        RestPage<TicketUser> rp = this.userService.getAllUsers(limit, offset);
        return rp;
    }
    @PutMapping("/role/{name}")
    public void updateRole(@RequestParam("mode") String mode) {

    }
}
