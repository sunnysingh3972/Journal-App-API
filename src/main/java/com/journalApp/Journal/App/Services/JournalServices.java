package com.journalApp.Journal.App.Services;

import com.journalApp.Journal.App.Entity.Journal;
import com.journalApp.Journal.App.Entity.User;
import com.journalApp.Journal.App.Repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalServices {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UserServices userServices;
    @Transactional
    public void addEntry(Journal journal, String username){
        User u=userServices.findByUsername(username);
        Journal j=journalRepository.save(journal);
        u.getJournalEnties().add(j);
        userServices.saveEntry(u);
    }
    public void addEntry(Journal journalEntry) {
        journalRepository.save(journalEntry);
    }
    public List<Journal> showAll(){
        return journalRepository.findAll();
    }
    public Optional<Journal> findById(ObjectId id){
        return journalRepository.findById(id);
    }
    public void deleteById(ObjectId id, String username){
        User u=userServices.findByUsername(username);
        u.getJournalEnties().removeIf(x->x.getId().equals(id));
        userServices.saveEntry(u);
        journalRepository.deleteById(id);
    }



}
