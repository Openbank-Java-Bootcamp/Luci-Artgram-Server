package com.ironhack.Artgramserver.repository;

import com.ironhack.Artgramserver.model.Love;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoveRepository extends JpaRepository<Love, Long> {
    //@Query(value = "SELECT love FROM Love WHERE love.painting.id = :paintingId and love.user.id = :userId")
    Optional<Love> findByPaintingIdAndUserId(@Param("userId") Long userId, @Param("paintingId") Long paintingId);

    //@Query(value="SELECT COUNT(love.id) FROM Love love where love.painting.id = :paintingId")
    Long countByPaintingId(@Param("paintingId") Long paintingId);
}
