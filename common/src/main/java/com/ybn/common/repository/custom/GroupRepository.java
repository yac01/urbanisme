package com.ybn.common.repository.custom;

import com.ybn.common.collection.Group;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface GroupRepository extends MongoRepository<Group, ObjectId> {
    Optional<Group> findByName(String name);
    Collection<Group> findByNameIn(String[] names);
}
