package com.pk.messenger.exception;

public class MyUserNotFoundException extends RuntimeException {

    public MyUserNotFoundException(Long id) {
        super("Could not find user with user_id = " + id);
    }
}
