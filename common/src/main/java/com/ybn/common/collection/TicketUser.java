package com.ybn.common.collection;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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
    @Getter(AccessLevel.NONE)
    private Collection<Authority> authorities;
    @Getter(AccessLevel.NONE)
    @DBRef
    private Collection<Group> groups;

    public Collection<Authority> getAuthorities() {
        if (authorities == null) {
            authorities = new ArrayList<>();
        }
        return authorities;
    }

    public Collection<Group> getGroups() {
        if(groups == null) {
            groups = new ArrayList<>();
        }
        return groups;
    }
}
