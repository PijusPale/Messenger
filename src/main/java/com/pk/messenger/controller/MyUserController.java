package com.pk.messenger.controller;

import com.pk.messenger.exception.MyUserNotFoundException;
import com.pk.messenger.model.MyUser;
import com.pk.messenger.repository.MyUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyUserController {
    private final MyUserRepository myUserRepository;
    MyUserController(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }
    @GetMapping("/myUsers")
    List<MyUser> all() {
        return myUserRepository.findAll();
    }
    @PostMapping("/myUsers")
    MyUser newMyUser(@RequestBody MyUser newMyUser) {
        return myUserRepository.save(newMyUser);
    }
    @GetMapping("/myUsers/{id}")
    MyUser one(@PathVariable Long id) {

        return myUserRepository.findById(id)
                .orElseThrow(() -> new MyUserNotFoundException(id));
    }

    @PutMapping("/myUsers/{id}")
    MyUser replaceMyUser(@RequestBody MyUser newMyUser, @PathVariable Long id) {

        return myUserRepository.findById(id)
                .map(myUser -> {
                    myUser.setUsername(newMyUser.getUsername());
                    myUser.setRole(newMyUser.getRole());
                    return myUserRepository.save(myUser);
                })
                .orElseGet(() -> {
                    newMyUser.setId(id);
                    return myUserRepository.save(newMyUser);
                });
    }

    @DeleteMapping("/myUsers/{id}")
    void deleteMyUser(@PathVariable Long id) {
        myUserRepository.deleteById(id);
    }
}
