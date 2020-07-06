package com.ybn.common.collection;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Document
public class Issue {
    @Id
    private ObjectId id;
    private String title;
    private LocalDate created;
    private LocalDate modified;
    private String description;
    private boolean closed;
    private String status;
    @DBRef
    private TicketUser author;
    @DBRef
    private Collection<Group> groups;
    private Collection<Comment> comments;

}
