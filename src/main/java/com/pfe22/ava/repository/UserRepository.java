package com.pfe22.ava.repository;

import com.pfe22.ava.entities.AppUsers.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    User findUserByUserId(String userId);


}
