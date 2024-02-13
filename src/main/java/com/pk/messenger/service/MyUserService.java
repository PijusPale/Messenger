package com.pk.messenger.service;

import com.pk.messenger.dto.MyUserRequestDTO;
import com.pk.messenger.model.MyUser;
import com.pk.messenger.model.MyUserStats;

public interface MyUserService {
    MyUser createMyUser(MyUserRequestDTO myUserRequest);
    MyUser getMyUserById(Long id);
    void deleteMyUserById(Long id);
    MyUserStats getMyUserStats(Long id);
}
