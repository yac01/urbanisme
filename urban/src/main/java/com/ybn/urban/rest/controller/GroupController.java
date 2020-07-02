package com.ybn.urban.rest.controller;

import com.ybn.common.collection.Group;
import com.ybn.common.dto.GroupDto;
import com.ybn.urban.rest.technical.RestPage;
import com.ybn.urban.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/urban/group")
public class GroupController {
    @Autowired
    private IGroupService service;
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody  GroupDto groupDto) {
        this.service.create(groupDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public RestPage<Group> findAllWithPagination(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset) {
        return this.service.findAll(limit, offset);
    }

    @GetMapping("/list/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(this.service.all());
    }

    @PutMapping("/delete")
    public ResponseEntity delete(@RequestParam("name") String name) {
        this.service.delete(name);
        return ResponseEntity.noContent().build();
    }
}
