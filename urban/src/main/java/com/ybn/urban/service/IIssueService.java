package com.ybn.urban.service;

import com.ybn.common.collection.Issue;
import com.ybn.common.dto.IssueDto;
import com.ybn.urban.rest.technical.RestPage;
import org.springframework.data.domain.Pageable;

public interface IIssueService {
    /**
     * service for creating an issue
     * @param issueDto
     */
    Issue create(IssueDto issueDto);

    RestPage<Issue> findUsersIssues(IssueDto issueDto, Pageable pageable);
}
