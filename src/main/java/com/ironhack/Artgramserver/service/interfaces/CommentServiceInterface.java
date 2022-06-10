package com.ironhack.Artgramserver.service.interfaces;

import com.ironhack.Artgramserver.DTO.CommentDTO;
import com.ironhack.Artgramserver.model.Painting;
import com.ironhack.Artgramserver.model.User;

public interface CommentServiceInterface {
    void saveComment(CommentDTO commentDTO);
    void deleteComment(Long id);

}
