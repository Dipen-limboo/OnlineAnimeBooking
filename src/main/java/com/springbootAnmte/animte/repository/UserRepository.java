package com.springbootAnmte.animte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbootAnmte.animte.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByResetPasswordToken(String token); 
}
