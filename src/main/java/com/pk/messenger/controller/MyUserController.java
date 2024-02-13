package com.pk.messenger.controller;

import com.pk.messenger.dto.MyUserRequestDTO;
import com.pk.messenger.exception.MyUserNotFoundException;
import com.pk.messenger.model.MyUser;
import com.pk.messenger.repository.MyUserRepository;
import com.pk.messenger.service.MyUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyUserController {
    private final MyUserService myUserService;
    MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }
//    @GetMapping("/myUsers")
//    List<MyUser> all() {
//        return myUserRepository.findAll();
//    }
    @PostMapping("/myUsers")
    MyUser newMyUser(@RequestBody MyUserRequestDTO newMyUser) {
        return myUserService.createMyUser(newMyUser);
    }
    @GetMapping("/myUsers/{id}")
    MyUser getMyUserById(@PathVariable Long id) {

        return myUserService.getMyUserById(id);
    }

//    @PutMapping("/myUsers/{id}")
//    MyUser replaceMyUser(@RequestBody MyUser newMyUser, @PathVariable Long id) {
//
//        return myUserRepository.findById(id)
//                .map(myUser -> {
//                    myUser.setUsername(newMyUser.getUsername());
//                    myUser.setRole(newMyUser.getRole());
//                    return myUserRepository.save(myUser);
//                })
//                .orElseGet(() -> {
//                    newMyUser.setId(id);
//                    return myUserRepository.save(newMyUser);
//                });
//    }

    @DeleteMapping("/myUsers/{id}")
    void deleteMyUser(@PathVariable Long id) {
        myUserService.deleteMyUserById(id);
    }
}
