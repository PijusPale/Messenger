package com.pk.messenger.model;

import com.pk.messenger.enums.UserRole;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UserRole userRole;
    private String username;

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
