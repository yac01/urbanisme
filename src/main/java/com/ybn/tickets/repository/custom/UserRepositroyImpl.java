package com.ybn.tickets.repository.custom;

import com.ybn.tickets.collection.TicketUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class UserRepositroyImpl implements UserRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public boolean isUnique(String field, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(value));
        return !mongoTemplate.exists(query, TicketUser.class);
    }
}
