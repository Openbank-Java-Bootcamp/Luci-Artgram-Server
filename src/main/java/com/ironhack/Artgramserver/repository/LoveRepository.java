package com.ironhack.Artgramserver.repository;

import com.ironhack.Artgramserver.model.Love;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoveRepository extends JpaRepository<Love, Long> {
}
