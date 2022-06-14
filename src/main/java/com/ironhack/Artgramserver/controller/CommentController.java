package com.ironhack.Artgramserver.controller;

import com.ironhack.Artgramserver.DTO.CommentDTO;
import com.ironhack.Artgramserver.model.Comment;
import com.ironhack.Artgramserver.model.Painting;
import com.ironhack.Artgramserver.service.impl.CommentService;
import com.ironhack.Artgramserver.service.impl.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/paintings")
public class CommentController {

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment getCommentById(@PathVariable(name = "id") Long commentId) {
        return commentService.findById(commentId);
    }

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@Valid @RequestBody CommentDTO comment, Authentication authentication){
       commentService.saveComment(comment, authentication);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable(name = "id") Long commentId){
        commentService.deleteComment(commentId);
    }
}
