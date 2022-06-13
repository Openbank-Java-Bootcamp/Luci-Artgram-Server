package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.model.Painting;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PaintingServiceInterface {
    Painting findById(Long id);
    List<Painting> findByUserId(Long userId);
    void savePainting(Painting painting, Authentication authentication);
    void updatePainting(Long id, Painting painting);
    void deletePainting(Long id);
}
