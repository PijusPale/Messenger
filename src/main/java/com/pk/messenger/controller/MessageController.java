package com.pk.messenger.controller;

import com.pk.messenger.exception.MessageNotFoundException;
import com.pk.messenger.model.Message;
import com.pk.messenger.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageRepository messageRepository;
    MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    @GetMapping("/messages")
    List<Message> all() {
        return messageRepository.findAll();
    }
    @PostMapping("/messages")
    Message newMessage(@RequestBody Message newMessage) {
        return messageRepository.save(newMessage);
    }
    @GetMapping("/messages/{id}")
    Message one(@PathVariable Long id) {

        return messageRepository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @PutMapping("/messages/{id}")
    Message replaceMessage(@RequestBody Message newMessage, @PathVariable Long id) {

        return messageRepository.findById(id)
                .map(message -> {
                    message.setText(newMessage.getText());
                    message.setDate(newMessage.getDate());
                    message.setUserId(newMessage.getUserId());
                    return messageRepository.save(message);
                })
                .orElseGet(() -> {
                    newMessage.setId(id);
                    return messageRepository.save(newMessage);
                });
    }

    @DeleteMapping("/messages/{id}")
    void deleteMessage(@PathVariable Long id) {
        messageRepository.deleteById(id);
    }
}
