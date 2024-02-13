package com.pk.messenger.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long id) {
        super("Could not find message with message_id = " + id);
    }
}
