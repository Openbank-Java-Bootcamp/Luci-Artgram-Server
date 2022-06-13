package com.ironhack.Artgramserver.repository;

import com.ironhack.Artgramserver.model.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
    List<Painting> findByUserId(Long userId);
}
