package com.pk.messenger.controller;

import com.pk.messenger.dto.MyUserRequestDTO;
import com.pk.messenger.enums.UserRole;
import com.pk.messenger.model.MyUser;
import com.pk.messenger.model.MyUserStats;
import com.pk.messenger.service.MyUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MyUserController {
    private final MyUserService myUserService;
    MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping("/myUsers")
    MyUser newMyUser(@RequestBody MyUserRequestDTO newMyUser, @RequestParam UserRole userRole) {
        if (userRole == UserRole.ADMIN){
        return myUserService.createMyUser(newMyUser);
        } else {
            throw(new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized"));
        }
    }
    @GetMapping("/myUsers/{id}")
    MyUser getMyUserById(@PathVariable Long id, @RequestParam UserRole userRole) {
        if (userRole == UserRole.ADMIN){
            return myUserService.getMyUserById(id);
        } else {
            throw(new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized"));
        }

    }

    @DeleteMapping("/myUsers/{id}")
    void deleteMyUser(@PathVariable Long id, @RequestParam UserRole userRole) {
        if (userRole == UserRole.ADMIN){
            myUserService.deleteMyUserById(id);
        } else {
            throw(new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized"));
        }
    }
    @GetMapping(value = "myUsers/statistics/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMyUserStats(@PathVariable Long id, @RequestParam UserRole userRole) {
        if (userRole == UserRole.ADMIN) {
            MyUserStats stats = myUserService.getMyUserStats(id);
            return ResponseEntity.ok(stats);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized");
        }
    }
}
