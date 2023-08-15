package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public User(String email, String password, List<String> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public void updateId(Long id) {
        this.id = id;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateRoles(List<String> roles) {
        this.roles = roles;
    }

    public static User of(String email, String password) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");
        return new User(email, password, roles);
    }
}
