package com.journalApp.Journal.App.Repository;

import com.journalApp.Journal.App.Entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<Journal, ObjectId> {
}
