package com.ybn.common.collection;

import com.ybn.common.collection.TicketUser;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Comment {
    @Id
    private ObjectId id;
    @DBRef
    private TicketUser author;
    private LocalDate createdAt;
    private String content;
}
