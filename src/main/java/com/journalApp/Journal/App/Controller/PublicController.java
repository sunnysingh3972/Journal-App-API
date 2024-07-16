package com.journalApp.Journal.App.Controller;

import com.journalApp.Journal.App.Entity.User;
import com.journalApp.Journal.App.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserServices userServices;
    @PostMapping("/create-entry")
    public boolean saveEntry(@RequestBody User user){
        userServices.saveEntry(user);
        return true;
    }
}
