package com.ironhack.Artgramserver.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Painting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String picturePath;
    private String title;
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "painting", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "painting")
    private List<Love> loves;

}
