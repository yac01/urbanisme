package com.ybn.common.collection;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Group {
    @Id
    private ObjectId id;
    private String name;
}
