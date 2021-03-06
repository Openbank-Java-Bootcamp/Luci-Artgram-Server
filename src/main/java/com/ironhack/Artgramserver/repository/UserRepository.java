package com.ironhack.Artgramserver.repository;

import com.ironhack.Artgramserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
   Optional<User> findByName(String name);
}
