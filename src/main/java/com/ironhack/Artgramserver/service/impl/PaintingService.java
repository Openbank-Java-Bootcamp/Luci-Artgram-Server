package com.ironhack.Artgramserver.service.impl;

import com.ironhack.Artgramserver.model.Painting;
import com.ironhack.Artgramserver.model.User;
import com.ironhack.Artgramserver.repository.PaintingRepository;
import com.ironhack.Artgramserver.repository.UserRepository;
import com.ironhack.Artgramserver.service.interfaces.PaintingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class PaintingService implements PaintingServiceInterface {

    @Autowired
    private PaintingRepository paintingRepository;

    @Autowired
    private UserRepository userRepository;

    // @desc find one Painting by its id
    public Painting findById(Long id) {
        return paintingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting not found"));
    }

    // @desc find one Painting by its user Id
    public List<Painting> findByUserId(Long userId) {
        return paintingRepository.findByUserId(userId);
    }

    // @desc save new Painting
    public void savePainting(Painting painting, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User userFromDb = userRepository.findByEmail(email);

        if(painting.getId() != null) {
            Optional<Painting> optionalPainting = paintingRepository.findById(painting.getId());
            if(optionalPainting.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Painting with id " + painting.getId() + " already exist");

        }
        painting.setUser(userFromDb);
        paintingRepository.save(painting);
    }

    // @desc edit one painting getting by its id
    public void updatePainting(Long id, Painting painting) {
        Painting paintingFromDB = paintingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting not found"));
        paintingFromDB.setPicturePath(painting.getPicturePath());
        paintingFromDB.setDescription(painting.getDescription());
        paintingFromDB.setTitle(painting.getTitle());
        paintingRepository.save(paintingFromDB);
    }

    // @desc delete one Painting getting it by its id
    public void deletePainting(Long id) {
    Painting paintingFromDB = paintingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting not found"));
        paintingRepository.deleteById(id);
    }
}
