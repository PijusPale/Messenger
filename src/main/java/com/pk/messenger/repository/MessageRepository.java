package com.pk.messenger.repository;

import com.pk.messenger.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(nativeQuery = true, value = "SELECT m.* " +
            "FROM my_User mu " +
            "JOIN message m ON mu.id = m.user_Id " +
            "WHERE mu.id = :userId")
    List<Message> findAllByUserId(@Param("userId") Long userId);
}
