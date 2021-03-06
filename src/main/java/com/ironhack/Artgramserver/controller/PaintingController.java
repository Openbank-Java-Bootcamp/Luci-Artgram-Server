package com.ironhack.Artgramserver.controller;

import com.ironhack.Artgramserver.model.Painting;
import com.ironhack.Artgramserver.repository.PaintingRepository;
import com.ironhack.Artgramserver.service.impl.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PaintingController {

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private PaintingRepository paintingRepository;

    @GetMapping("/paintings")
    @ResponseStatus(HttpStatus.OK)
    public List<Painting> getPaintings() {
        return paintingRepository.findAll();
    }

    @GetMapping("/paintings/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Painting> getPaintingsByUserId(@PathVariable Long userId) {
        return paintingService.findByUserId(userId);
    }

    @GetMapping("/paintings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Painting getPaintingById(@PathVariable(name = "id") Long paintingId) {
        return paintingService.findById(paintingId);
    }

    @PostMapping("/paintings")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPainting(@RequestBody @Valid Painting painting, Authentication authentication){
        paintingService.savePainting(painting, authentication);
    }

    @PutMapping("/paintings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePainting(@PathVariable Long id,@RequestBody @Valid Painting painting){
        paintingService.updatePainting(id, painting);
    }

    @DeleteMapping("/paintings/{paintingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePainting(@PathVariable(name = "paintingId") Long id){
        paintingService.deletePainting(id);
    }
}
