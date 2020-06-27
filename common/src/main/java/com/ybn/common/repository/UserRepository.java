package com.ybn.common.repository;

import com.ybn.common.collection.TicketUser;
import com.ybn.common.repository.custom.UserRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<TicketUser, ObjectId>, UserRepositoryCustom {
    /**
     * retrieve a user by its username
     * @param username string representation of username
     * @return user if found or optional empty
     * */
    Optional<TicketUser> findByUsername(String username);
}
