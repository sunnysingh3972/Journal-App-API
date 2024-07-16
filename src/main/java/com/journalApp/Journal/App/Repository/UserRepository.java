package com.journalApp.Journal.App.Repository;

import com.journalApp.Journal.App.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByusername(String username);
    void deleteByusername(String username);
}
