package com.ironhack.Artgramserver.controller;

import com.ironhack.Artgramserver.model.User;
import com.ironhack.Artgramserver.repository.UserRepository;
import com.ironhack.Artgramserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable(name="id") Long userId){
        return userService.findById(userId);
    }

    @GetMapping("/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByName(@RequestBody String userName){
        return userService.getUserByName(userName);
    }

    @PutMapping("/profile/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id,@RequestBody @Valid User user){
        userService.updateUser(id, user);
    }
}
