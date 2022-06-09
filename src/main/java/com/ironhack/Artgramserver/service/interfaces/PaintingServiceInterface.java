package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.model.Painting;

public interface PaintingServiceInterface {
    Painting findById(Long id);
    void savePainting(Painting painting);
    void updatePainting(Long id, Painting painting);
    void deletePainting(Long id);
}
