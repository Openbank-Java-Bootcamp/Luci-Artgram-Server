package com.ironhack.Artgramserver.controller;

import com.ironhack.Artgramserver.DTO.CommentDTO;
import com.ironhack.Artgramserver.service.impl.CommentService;
import com.ironhack.Artgramserver.service.impl.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/paintings")
public class CommentController {

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/{paintingId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@Valid @RequestBody CommentDTO comment){
       commentService.saveComment(comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
