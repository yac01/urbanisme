package com.ybn.common.repository;

import com.ybn.common.collection.Authority;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends MongoRepository<Authority, ObjectId> {
    Optional<Authority> findByRole(String role);
}
