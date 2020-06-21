package com.ybn.tickets.collection;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@Document
public class TicketUser {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String email;
    private boolean activeAccount;
    private Collection<Authority> authorities;
}
