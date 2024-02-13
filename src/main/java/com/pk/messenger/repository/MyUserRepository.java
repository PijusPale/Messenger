package com.pk.messenger.repository;

import com.pk.messenger.model.Message;
import com.pk.messenger.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM my_User WHERE username = :username")
    MyUser findByUsername(@Param("username") String username);
}
