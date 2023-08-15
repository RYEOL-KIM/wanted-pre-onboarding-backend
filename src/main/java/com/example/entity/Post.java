package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(User user, String email, String title, String content) {
        this.user = user;
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public static Post of(User user, String email, String title, String content) {
        return new Post(user, email, title, content);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
