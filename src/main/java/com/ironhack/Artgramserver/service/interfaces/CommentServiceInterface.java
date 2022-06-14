package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.DTO.CommentDTO;
import com.ironhack.Artgramserver.model.Comment;
import com.ironhack.Artgramserver.model.Painting;
import com.ironhack.Artgramserver.model.User;
import org.springframework.security.core.Authentication;

public interface CommentServiceInterface {
    Comment findById(Long id);
    void saveComment(CommentDTO commentDTO, Authentication authentication);
    void deleteComment(Long id);

}
