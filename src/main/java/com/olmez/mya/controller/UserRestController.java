package com.olmez.mya.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.model.User;
import com.olmez.mya.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200") // This allows to talk to port:5000 (ui-backend)
public class UserRestController {

    private final UserService userService;

    // CREATE = POST
    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // READ = GET
    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // UPDATE = PUT
    @PutMapping("/update/{userId}")
    public User updateUser(@PathVariable("userId") long id, @RequestBody User model) {
        return userService.updateUser(id, model);
    }

    // DELETE = DELETE
    @DeleteMapping("/delete/{userId}")
    public boolean deleteUser(@PathVariable("userId") long id) {
        return userService.deleteUser(id);
    }

    // DELETE = DELETE using PARAM
    @DeleteMapping("/delete")
    public boolean deleteUserByParam(@RequestParam long id) {
        return userService.deleteUser(id);
    }

    //
    @GetMapping("/{id}")
    public User getUserById(@RequestParam long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public User getUserById(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

}
