package com.pk.messenger.service;

import com.pk.messenger.dto.MessageRequestDTO;
import com.pk.messenger.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessages();
    List<Message> getMessagesSorted();
    Message createMessage(MessageRequestDTO messageRequest);
    Message updateMessage(Long id, MessageRequestDTO messageRequest);
    List<Message> getAllByUserId(Long userId);
}
