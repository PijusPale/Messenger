package com.pk.messenger.service;

import com.pk.messenger.dto.MyUserRequestDTO;
import com.pk.messenger.model.MyUser;

public interface MyUserService {
    MyUser createMyUser(MyUserRequestDTO myUserRequest);
    MyUser getMyUserById(Long id);
    void deleteMyUserById(Long id);
}
