package com.ybn.tickets.repository;

import com.ybn.tickets.collection.TicketUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<TicketUser, ObjectId> {
    /**
     * retrieve a user by its username
     * @param username string representation of username
     * @return user if found or optional empty
     * */
    Optional<TicketUser> findByUsername(String username);
}
