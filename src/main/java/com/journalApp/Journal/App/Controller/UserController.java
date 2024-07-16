package com.journalApp.Journal.App.Controller;

import com.journalApp.Journal.App.Entity.User;
import com.journalApp.Journal.App.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PutMapping
    public User upgradeByUserName(@RequestBody User user){
      String username=SecurityContextHolder.getContext().getAuthentication().getName();
      User u=userServices.findByUsername(username);
        if(u!=null){
            if(!u.getUsername().equals(user.getUsername()))
            {
                u.setUsername(user.getUsername());
            }

            u.setPassword(user.getPassword());

        }
        return u;
    }
    @DeleteMapping
    public void deleteByUsername()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        userServices.deleteByUsername(username);
    }
}
