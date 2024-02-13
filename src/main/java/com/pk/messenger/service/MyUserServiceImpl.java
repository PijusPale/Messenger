package com.pk.messenger.service;

import com.pk.messenger.dto.MessageRequestDTO;
import com.pk.messenger.dto.MyUserRequestDTO;
import com.pk.messenger.exception.MyUserNotFoundException;
import com.pk.messenger.model.Message;
import com.pk.messenger.model.MyUser;
import com.pk.messenger.model.MyUserStats;
import com.pk.messenger.repository.MyUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.Date;
import java.util.OptionalDouble;
import java.util.Set;

@Service
public class MyUserServiceImpl implements MyUserService {
    private final MyUserRepository myUserRepository;
    private final MessageService messageService;

    public MyUserServiceImpl(MyUserRepository myUserRepository, MessageService messageService) {
        this.myUserRepository = myUserRepository;
        this.messageService = messageService;
    }

    @Override
    public MyUser createMyUser(MyUserRequestDTO myUserRequest) {
        if (myUserRepository.findByUsername(myUserRequest.getUsername()) != null) {
            throw (
                    new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with id %s already exists", myUserRequest.getUsername()))
            );
        }
        return myUserRepository.save(new MyUser(myUserRequest.getRole(), myUserRequest.getUsername()));
    }

    @Override
    public MyUser getMyUserById(Long id) {
        return myUserRepository.findById(id).orElseThrow(
                () -> new MyUserNotFoundException(id)
        );
    }

    @Override
    public void deleteMyUserById(Long id) {

        getMyUserById(id).getMessages().forEach(message -> {
            MessageRequestDTO updatedMessage =
                    new MessageRequestDTO(
                            message.getText(),
                            message.getDate(),
                            myUserRepository.findByUsername("anonymous").getId());
            messageService.updateMessage(message.getId(), updatedMessage);
        });

        myUserRepository.deleteById(id);
    }

    @Override
    public MyUserStats getMyUserStats(Long id) {

        MyUser myUser = myUserRepository.findById(id).orElseThrow(
                () -> new MyUserNotFoundException(id)
        );
        Set<Message> messages = myUser.getMessages();
        if (messages.isEmpty()) {
            return new MyUserStats(myUser, 0, null, null, 0, null);
        }
        Date firstMessageDate = messages.stream()
                .min(Comparator.comparing(Message::getDate))
                .map(Message::getDate)
                .orElse(null);

        Date lastMessageDate = messages.stream()
                .max(Comparator.comparing(Message::getDate))
                .map(Message::getDate)
                .orElse(null);

        OptionalDouble averageMessageLength = messages.stream()
                .mapToInt(message -> message.getText().length())
                .average();

        String lastMessageText = messages.stream()
                .max(Comparator.comparing(Message::getDate))
                .map(Message::getText)
                .orElse("");

        return new MyUserStats(
                myUser,
                messages.size(),
                firstMessageDate,
                lastMessageDate,
                averageMessageLength.isPresent() ? averageMessageLength.getAsDouble() : 0,
                lastMessageText
        );
    }
}
