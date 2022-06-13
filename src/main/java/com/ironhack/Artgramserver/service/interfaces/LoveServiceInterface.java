package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.DTO.LoveCountDTO;
import com.ironhack.Artgramserver.model.Love;
import com.ironhack.Artgramserver.model.User;

public interface LoveServiceInterface {

    Love addPaintingLove(Long paintingId, User currentUser);

    LoveCountDTO getLovesCountByPaintingId(Long paintingId);

}
