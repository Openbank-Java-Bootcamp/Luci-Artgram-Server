package com.ironhack.Artgramserver.service.impl;

import com.ironhack.Artgramserver.model.User;
import com.ironhack.Artgramserver.repository.RoleRepository;
import com.ironhack.Artgramserver.repository.UserRepository;
import com.ironhack.Artgramserver.service.interfaces.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserService implements UserServiceInterface, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    // @desc find one User by his id
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // @desc find one User by his name
    public User getUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // @desc add a new User when Sign Up
    public User saveUser(User userSignupDTO) {
        log.info("Saving a new user {} inside of the database", userSignupDTO.getName());
        User user = new User(userSignupDTO.getName(), userSignupDTO.getEmail(), userSignupDTO.getPassword(), userSignupDTO.getAvatar());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // @desc Update User info
    @Override
    public void updateUser(Long id, User user) {
        User userFromDB = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));
        user.setId(userFromDB.getId());
        userRepository.save(user);
    }

    // @desc get a list of all users
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    // @desc find one User to Login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User is found in the database: {}", email);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
    }
}
