package com.ironhack.Artgramserver.DTO;

import com.ironhack.Artgramserver.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @NotNull
    @Size(max = 50)
    private String comment;
    private Long paintingId;
    private Long userId;
}
