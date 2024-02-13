package com.pk.messenger.service;

import com.pk.messenger.dto.MessageRequestDTO;
import com.pk.messenger.dto.MyUserRequestDTO;
import com.pk.messenger.model.Message;
import com.pk.messenger.model.MyUser;
import com.pk.messenger.repository.MessageRepository;
import com.pk.messenger.repository.MyUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MyUserServiceImpl implements MyUserService{
    private final MyUserRepository myUserRepository;
    private final MessageService messageService;

    public MyUserServiceImpl(MyUserRepository myUserRepository, MessageService messageService) {
        this.myUserRepository = myUserRepository;
        this.messageService = messageService;
    }

    @Override
    public MyUser createMyUser(MyUserRequestDTO myUserRequest) {
        if (myUserRepository.findByUsername(myUserRequest.getUsername()) != null){
            throw(
                    new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with id %s already exists", myUserRequest.getUsername()))
            );
        }
        return myUserRepository.save(new MyUser(myUserRequest.getRole(), myUserRequest.getUsername()));
    }

    @Override
    public MyUser getMyUserById(Long id) {
        return myUserRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %d not found", id))
        );
    }

    @Override
    public void deleteMyUserById(Long id) {

        messageService.getAllByUserId(id).forEach(message -> {
            MessageRequestDTO updatedMessage =
                    new MessageRequestDTO(
                            message.getText(),
                            message.getDate(),
                            myUserRepository.findByUsername("anonymous").getId());
            messageService.updateMessage(message.getId(), updatedMessage);
        });

        myUserRepository.deleteById(id);
    }
}
