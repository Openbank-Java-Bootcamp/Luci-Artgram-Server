package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.model.Painting;
import org.springframework.security.core.Authentication;

public interface PaintingServiceInterface {
    Painting findById(Long id);
    void savePainting(Painting painting, Authentication authentication);
    void updatePainting(Long id, Painting painting);
    void deletePainting(Long id);
}
