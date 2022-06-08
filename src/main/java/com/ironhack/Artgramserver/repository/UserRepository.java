package com.ironhack.Artgramserver.repository;

import com.ironhack.Artgramserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
