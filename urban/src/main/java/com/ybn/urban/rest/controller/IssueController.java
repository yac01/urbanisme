package com.ybn.urban.rest.controller;

import com.ybn.common.dto.IssueDto;
import com.ybn.urban.rest.technical.RestPage;
import com.ybn.urban.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/list")
    public RestPage list(@RequestBody IssueDto issueDto, @RequestParam(value = "limit", required = false) Integer limit,
                         @RequestParam(value = "offset", required = false) Integer offset) {
        limit = limit == null ? Integer.MAX_VALUE : limit;
        offset = offset == null ? 0 : offset;
        return this.issueService.findUsersIssues(issueDto, PageRequest.of(offset, limit));
    }
}
