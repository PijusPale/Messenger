package com.pk.messenger.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.messenger.controller.MyUserController;
import com.pk.messenger.enums.UserRole;
import com.pk.messenger.model.Message;
import com.pk.messenger.model.MyUser;
import com.pk.messenger.model.MyUserStats;
import com.pk.messenger.service.MyUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MyUserController.class)
public class MyUserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserService myUserService;

    @Autowired
    private ObjectMapper objectMapper;

    private MyUser myUser;
    private MyUserStats myUserStats;

    @BeforeEach
    void setUp() {

        myUser = new MyUser(UserRole.ADMIN, "testUser");
        myUser.setId(1L);

        Message message1 = new Message("Hello World", new Date(), myUser.getId());
        message1.setId(1L);

        Message message2 = new Message("Second Message", new Date(), myUser.getId());
        message2.setId(2L);

        Set<Message> messages = new HashSet<>(List.of(message1, message2));
        myUser.setMessages(messages);

        int totalLength = myUser.getMessages().stream().mapToInt(m -> m.getText().length()).sum();
        myUserStats = new MyUserStats(
                myUser,
                myUser.getMessages().size(),
                message1.getDate(),
                message2.getDate(),
                myUser.getMessages().isEmpty() ? 0 : (double) totalLength / myUser.getMessages().size(),
                message2.getText());
    }


    @Test
    void newMyUser_whenAdmin_shouldCreateUser() throws Exception {
        given(myUserService.createMyUser(ArgumentMatchers.any())).willReturn(myUser);

        mockMvc.perform(post("/myUsers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(myUser))
                        .param("userRole", UserRole.ADMIN.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(myUser.getUsername()));
    }

    @Test
    void getMyUserById_whenAdmin_shouldReturnUser() throws Exception {
        given(myUserService.getMyUserById(1L)).willReturn(myUser);

        mockMvc.perform(get("/myUsers/{id}", 1)
                        .param("userRole", UserRole.ADMIN.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(myUser.getUsername()));
    }

    @Test
    void deleteMyUser_whenAdmin_shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/myUsers/{id}", 1)
                        .param("userRole", UserRole.ADMIN.toString()))
                .andExpect(status().isOk());

        // Optionally verify myUserService.deleteMyUserById was called
    }

    @Test
    void getMyUserStats_whenAdmin_shouldReturnStats() throws Exception {
        given(myUserService.getMyUserStats(1L)).willReturn(myUserStats);

        mockMvc.perform(get("/myUsers/statistics/{id}", 1)
                        .param("userRole", UserRole.ADMIN.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add JSON path checks for stats as necessary
    }

    @Test
    void whenNotAdmin_shouldReturnForbidden() throws Exception {
        // This is a generic test to ensure non-admin users are forbidden
        // It can be replicated or parameterized for each endpoint as necessary

        mockMvc.perform(get("/myUsers/{id}", 1)
                        .param("userRole", UserRole.PRIVATE.toString())) // Assuming USER is a non-admin role
                .andExpect(status().isForbidden());
    }
}
