package com.ybn.urban.rest.controller;

import com.ybn.common.dto.IssueDto;
import com.ybn.urban.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/urban/issue")
public class IssueController {
    @Autowired
    private IIssueService issueService;


    @PostMapping("/create")
    public void create(@RequestBody IssueDto issueDto) {
        this.issueService.create(issueDto);
    }
}
