package com.devsblog1.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post_Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    private int likes;

    @OneToOne
    @JoinColumn(name = "postId", referencedColumnName = "id")
    private Posts posts;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

}
