package com.ironhack.Artgramserver.controller;

import com.ironhack.Artgramserver.DTO.LoveCountDTO;
import com.ironhack.Artgramserver.model.Love;
import com.ironhack.Artgramserver.model.User;
import com.ironhack.Artgramserver.service.impl.LoveService;
import com.ironhack.Artgramserver.service.impl.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paintings")
public class LoveController {

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private LoveService loveService;

    @GetMapping("{paintingId}/love/count")
    public LoveCountDTO getLovesCountByPaintingId(@PathVariable Long paintingId) {
        return loveService.getLovesCountByPaintingId(paintingId);
    }

    @PostMapping("{paintingId}/love")
    public Love lovePainting(@PathVariable Long paintingId, User currentUser) {
        return loveService.addPaintingLove(paintingId, currentUser);
    }
}
