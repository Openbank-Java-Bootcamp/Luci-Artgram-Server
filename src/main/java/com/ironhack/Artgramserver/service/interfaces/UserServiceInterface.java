package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.model.User;

import java.util.List;

public interface UserServiceInterface {
    User findById(Long id);
    User getUserByName(String name);
    User saveUser(User userSignupDTO);
    void updateUser(Long id, User user);
    List<User> getUsers();
}
