package com.journalApp.Journal.App.Services;

import com.journalApp.Journal.App.Entity.User;
import com.journalApp.Journal.App.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public Optional<User> getByID(ObjectId id){
        return userRepository.findById(id);
    }
    public User findByUsername(String username){
        return userRepository.findByusername(username);
    }
    public void deleteByUsername(String username){
        userRepository.deleteByusername(username);
    }

    public void saveAdmin(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER", "ADMIN"));
            userRepository.save(user);
    }
}
