package com.ybn.urban.service.impl;

import com.ybn.common.collection.Group;
import com.ybn.common.collection.Issue;
import com.ybn.common.collection.TicketUser;
import com.ybn.common.dto.IssueDto;
import com.ybn.common.repository.IssueRepository;
import com.ybn.common.repository.UserRepository;
import com.ybn.common.repository.custom.GroupRepository;
import com.ybn.urban.rest.exception.ExceptionKeyCode;
import com.ybn.urban.rest.exception.TicketException;
import com.ybn.urban.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IIssueService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Override
    public Issue create(IssueDto issueDto) {
        Collection<Group> groups = this.groupRepository.findByNameIn(issueDto.getGroups());
        if (groups == null || groups.isEmpty()) {
            throw new TicketException(ExceptionKeyCode.E_F_0005);
        }

        Optional<TicketUser> ouser = this.userRepository.findByUsername(issueDto.getAuthorName());
        if (!ouser.isPresent()) {
            throw new TicketException(ExceptionKeyCode.E_F_0006);
        }

        Issue issue = new Issue();
        issue.setCreated(LocalDate.now());
        issue.setAuthor(ouser.get());
        issue.setGroups(groups);
        issue.setClosed(false);
        issue.setModified(LocalDate.now());
        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getContent());
        return this.issueRepository.save(issue);
    }
}
