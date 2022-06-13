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

import java.util.Optional;
@Service
public class PaintingService implements PaintingServiceInterface {

    @Autowired
    private PaintingRepository paintingRepository;

    @Autowired
    private UserRepository userRepository;


    public Painting findById(Long id) {
        return paintingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting not found"));
    }


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


    public void updatePainting(Long id, Painting painting) {
        Painting paintingFromDB = paintingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting not found"));
        painting.setId(paintingFromDB.getId());
        paintingRepository.save(painting);
    }


    public void deletePainting(Long id) {
    Painting paintingFromDB = paintingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Painting not found"));
        paintingRepository.deleteById(id);
    }
}
