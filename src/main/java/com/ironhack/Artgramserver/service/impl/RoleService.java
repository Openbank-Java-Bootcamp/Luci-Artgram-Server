package com.ironhack.Artgramserver.service.impl;

import com.ironhack.Artgramserver.model.Role;
import com.ironhack.Artgramserver.model.User;
import com.ironhack.Artgramserver.repository.RoleRepository;
import com.ironhack.Artgramserver.repository.UserRepository;
import com.ironhack.Artgramserver.service.interfaces.RoleServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleService implements RoleServiceInterface {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // @desc save a Role
    public Role saveRole(Role role) {
        log.info("Saving a new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    // @desc add a Role to a User
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
