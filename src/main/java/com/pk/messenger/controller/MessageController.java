package com.pk.messenger.controller;

import com.pk.messenger.dto.MessageRequestDTO;
import com.pk.messenger.exception.MessageNotFoundException;
import com.pk.messenger.model.Message;
import com.pk.messenger.repository.MessageRepository;
import com.pk.messenger.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;
    MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    @GetMapping("/messages")
    List<Message> all() {
        return messageService.getMessages();
    }
    @GetMapping("/messagesSorted")
    List<Message> allSorted() {
        return messageService.getMessagesSorted();
    }
    @PostMapping("/messages")
    Message newMessage(@RequestBody MessageRequestDTO newMessage) {
        return messageService.createMessage(newMessage);
    }
//    @GetMapping("/messages/{id}")
//    Message one(@PathVariable Long id) {
//
//        return messageService.findById(id)
//                .orElseThrow(() -> new MessageNotFoundException(id));
//    }

    @PutMapping("/messages/{id}")
    Message replaceMessage(@RequestBody MessageRequestDTO newMessage, @PathVariable Long id) {
        return messageService.updateMessage(id, newMessage);
//        return messageRepository.findById(id)
//                .map(message -> {
//                    message.setText(newMessage.getText());
//                    message.setDate(newMessage.getDate());
//                    message.setUserId(newMessage.getUserId());
//                    return messageRepository.save(message);
//                })
//                .orElseGet(() -> {
//                    newMessage.setId(id);
//                    return messageRepository.save(newMessage);
//                });
    }

//    @DeleteMapping("/messages/{id}")
//    void deleteMessage(@PathVariable Long id) {
//        messageRepository.deleteById(id);
//    }
}
