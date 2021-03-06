package com.ironhack.Artgramserver.service.impl;

import com.ironhack.Artgramserver.DTO.CommentDTO;
import com.ironhack.Artgramserver.model.Comment;
import com.ironhack.Artgramserver.model.Painting;
import com.ironhack.Artgramserver.model.User;
import com.ironhack.Artgramserver.repository.CommentRepository;
import com.ironhack.Artgramserver.repository.PaintingRepository;
import com.ironhack.Artgramserver.repository.UserRepository;
import com.ironhack.Artgramserver.service.interfaces.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentService implements CommentServiceInterface {
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

    // @desc find one comment by its id
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
    }

    // @desc add new comment when user is login
    public void saveComment(CommentDTO commentDTO, Authentication authentication){
        System.out.println(commentDTO);
        String email = (String) authentication.getPrincipal();
        User userFromDb = userRepository.findByEmail(email);
        Comment newComment = new Comment();
        newComment.setComment(commentDTO.getComment());
        newComment.setPainting(paintingRepository.findById(commentDTO.getPaintingId()).get());
        newComment.setUser(userFromDb);
        commentRepository.save(newComment);

    }

    // @desc delete one comment
    public void deleteComment(Long id){
        Comment commentFromDB = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
        commentRepository.deleteById(id);
    }
}
