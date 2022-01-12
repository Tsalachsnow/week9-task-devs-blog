package com.devsblog1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;



@Entity
@AllArgsConstructor
@Table(name = "users")
public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(unique = true, nullable = false)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private String gender;

//        @OneToMany(mappedBy = "users" )
//        private List<Comments> comments;

        @OneToMany
//        @JoinColumn
        @JsonManagedReference
        private List<Posts> favoritePosts;

        @OneToMany(mappedBy = "users")
        private List<Post_Likes> post_likes;

        @OneToMany(mappedBy = "users")
        private List<Comment_Likes> comment_likes;

//        public List<Comments> getComments() {
//                return comments;
//        }
//
//        public void setComments(List<Comments> comments) {
//                this.comments = comments;
//        }

        public Users() {
        }

        public Users(Long id, String name, String email, String password, String gender) {
                this.id = id;
                this.name = name;
                this.email = email;
                this.password = password;
                this.gender = gender;
        }

        public void setFavoritePosts(List<Posts> favoritePosts) {
                this.favoritePosts = favoritePosts;
        }

        public List<Posts> getFavoritePosts() {
                return favoritePosts;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }
}

