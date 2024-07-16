package com.journalApp.Journal.App.Controller;

import com.journalApp.Journal.App.Entity.Journal;
import com.journalApp.Journal.App.Entity.User;
import com.journalApp.Journal.App.Services.JournalServices;
import com.journalApp.Journal.App.Services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    private JournalServices journalServices;
    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<?> saveEntry(@RequestBody Journal journal){
        try{
            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            String username=auth.getName();
            journalServices.addEntry(journal,username);
            return new ResponseEntity<>(journal,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> showAll(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User u=userServices.findByUsername(username);
        List<Journal> lst=u.getJournalEnties();
        if(lst!=null&&lst.size()>0){
            return new ResponseEntity<>(lst,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable ObjectId id){
        Journal journal=journalServices.findById(id).orElse(null);
        if(journal!=null){
            return new ResponseEntity<>(journal,HttpStatus.FOUND);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@RequestBody Journal journal,@PathVariable ObjectId id){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        Journal old_journal=journalServices.findById(id).orElse(null);
        if(old_journal!=null){
            old_journal.setTitle(journal.getTitle());
            if(journal.getContent()!=null)old_journal.setContent(journal.getContent());
            journalServices.addEntry(old_journal);
            return  new ResponseEntity<>(old_journal,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        Journal old_journal=journalServices.findById(id).orElse(null);
        if(old_journal!=null){
            journalServices.deleteById(id,username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
