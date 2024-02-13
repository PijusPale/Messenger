package com.pk.messenger.config;

import com.pk.messenger.enums.UserRole;
import com.pk.messenger.model.Message;
import com.pk.messenger.model.MyUser;
import com.pk.messenger.repository.MessageRepository;
import com.pk.messenger.repository.MyUserRepository;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(MyUserRepository myUserRepository, MessageRepository messageRepository) {
        MyUser preload_user = myUserRepository.save(new MyUser(UserRole.PRIVATE, "pikiz_private"));
        return args -> {
            log.info("Preloading " + myUserRepository.save(new MyUser(UserRole.ADMIN, "pikiz_admin")));
            log.info("Preloading " + preload_user);
            log.info("Preloading " + messageRepository.save(new Message("Goooood mooorning Vietnaam!", new Date(), preload_user.getId())));
        };
    }
}
