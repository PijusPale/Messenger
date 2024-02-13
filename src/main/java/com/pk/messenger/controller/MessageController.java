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

    @PutMapping("/messages/{id}")
    Message updateMessage(@RequestBody MessageRequestDTO newMessage, @PathVariable Long id) {
        return messageService.updateMessage(id, newMessage);
    }
}
