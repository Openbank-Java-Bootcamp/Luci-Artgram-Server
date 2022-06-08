package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User userSignupDTO);

    List<User> getUsers();
}
