package com.ironhack.Artgramserver.service.impl;

import com.ironhack.Artgramserver.DTO.LoveCountDTO;
import com.ironhack.Artgramserver.model.Love;
import com.ironhack.Artgramserver.model.Painting;
import com.ironhack.Artgramserver.model.User;
import com.ironhack.Artgramserver.repository.LoveRepository;
import com.ironhack.Artgramserver.repository.PaintingRepository;
import com.ironhack.Artgramserver.repository.UserRepository;
import com.ironhack.Artgramserver.service.interfaces.LoveServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoveService implements LoveServiceInterface {

    @Autowired
    private PaintingRepository paintingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoveRepository loveRepository;



    public Love addPaintingLove(Long paintingId, User currentUser) {
        Painting painting = paintingRepository.findById(paintingId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting not found"));
        Optional<User> user = userRepository.findById(currentUser.getId());
        Optional<Love> love = loveRepository.findLoveByPaintingIdByUserId(user.get().getId(), paintingId);

        if(love.isPresent()){
            loveRepository.deleteById(love.get().getId());
        }
        Love loveObject = new Love();
        loveObject.setUser(user.get());
        loveObject.setPainting(painting);
       return loveRepository.save(loveObject);

    }

    @Override
    public LoveCountDTO getLovesCountByPaintingId(Long paintingId) {
        Long count = loveRepository.countByPaintingId(paintingId);
        return new LoveCountDTO(count);
    }
}
