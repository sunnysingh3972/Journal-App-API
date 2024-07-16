package com.journalApp.Journal.App.Controller;

import com.journalApp.Journal.App.Api.WeatherResponse;
import com.journalApp.Journal.App.Entity.User;
import com.journalApp.Journal.App.Services.UserServices;
import com.journalApp.Journal.App.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
   private UserServices userServices;
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/get-user")
    public ResponseEntity<?> getAlluser(){
        List<User> u=userServices.getAll();
        if(u!=null){
            return new ResponseEntity<>(u, HttpStatus.FOUND);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get-weather")
    public ResponseEntity<?> getWeather(){
        WeatherResponse weatherResponse=weatherService.getWeather("Hazaribag");
        if(weatherResponse!=null) {
            return new ResponseEntity<>(weatherService.getWeather("Hazaribagh"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user) {
        userServices.saveAdmin(user);
    }

}
