package com.ybn.common.collection;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@Document("ticket_user")
public class TicketUser {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String email;
    private boolean activeAccount;
    @DBRef
    private Collection<Authority> authorities;
    @DBRef
    private Collection<Group> groups;
}
