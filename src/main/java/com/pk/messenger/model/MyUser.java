package com.pk.messenger.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pk.messenger.enums.UserRole;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MY_USER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String username;
    @OneToMany(mappedBy = "userId")
    @JsonManagedReference
    private Set<Message> messages = new HashSet<>();

    MyUser(){}
    public MyUser(UserRole role, String username){
        this.userRole = role;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public UserRole getRole() {
        return userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(UserRole role) {
        this.userRole = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return Objects.equals(getId(), myUser.getId()) && getRole() == myUser.getRole() && Objects.equals(getUsername(), myUser.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole(), getUsername());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + userRole +
                ", username='" + username + '\'' +
                '}';
    }
}
