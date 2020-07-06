package com.ybn.urban.service;

import com.ybn.common.collection.Issue;
import com.ybn.common.dto.IssueDto;

public interface IIssueService {
    /**
     * service for creating an issue
     * @param issueDto
     */
    Issue create(IssueDto issueDto);
}
