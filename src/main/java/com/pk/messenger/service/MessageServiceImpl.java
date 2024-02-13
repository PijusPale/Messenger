package com.pk.messenger.service;

import com.pk.messenger.dto.MessageRequestDTO;
import com.pk.messenger.model.Message;
import com.pk.messenger.repository.MessageRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getMessagesSorted() {
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @Override
    public Message createMessage(MessageRequestDTO messageRequest) {
        return messageRepository.save(new Message(messageRequest.getText(), messageRequest.getDate(), messageRequest.getUserId()));
    }

    @Override
    public Message updateMessage(Long id, MessageRequestDTO messageRequest) {
        return messageRepository.findById(id)
                .map(message -> {
                    message.setText(messageRequest.getText());
                    message.setDate(messageRequest.getDate());
                    message.setUserId(messageRequest.getUserId());
                    return messageRepository.save(message);
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Message with id %d not found", id))
                );
    }

    public List<Message> getAllByUserId(Long userId){
        return messageRepository.findAllByUserId(userId);
    }
}
