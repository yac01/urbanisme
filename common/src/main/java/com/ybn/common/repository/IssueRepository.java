package com.ybn.common.repository;

import com.ybn.common.collection.Group;
import com.ybn.common.collection.Issue;
import com.ybn.common.collection.TicketUser;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IssueRepository extends MongoRepository<Issue, ObjectId> {

    Page<Issue> findByAuthorOrAuthorGroupsIn(TicketUser author, Collection<Group> groups, Pageable pageable);
}
