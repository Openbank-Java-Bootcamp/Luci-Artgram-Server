package com.ironhack.Artgramserver.service.impl;

import com.ironhack.Artgramserver.DTO.CommentDTO;
import com.ironhack.Artgramserver.model.Comment;
import com.ironhack.Artgramserver.repository.CommentRepository;
import com.ironhack.Artgramserver.repository.PaintingRepository;
import com.ironhack.Artgramserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PaintingRepository paintingRepository;

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void saveComment(CommentDTO commentDTO){
        Comment newComment = new Comment();
        newComment.setComment(commentDTO.getComment());
        newComment.setPainting(paintingRepository.findById(commentDTO.getPaintingId()).get());
        newComment.setUser(userRepository.findById(commentDTO.getUserId()).get());
        newComment.setUser(userRepository.findByName(commentDTO.getUserName().getName()).get());
        commentRepository.save(newComment);



    }

    public void deleteComment(Long id){
        Comment commentFromDB = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
        commentRepository.deleteById(id);
    }
}
