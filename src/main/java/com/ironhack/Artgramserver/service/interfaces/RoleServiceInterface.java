package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.model.Role;

public interface RoleServiceInterface {
    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);
}
