package com.example.mysecurity.repository;


import com.example.mysecurity.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    // JpaRepository 기능
    // findBy속성명 -> 찾는 메서드를 만들어줌
    Optional<UserAccount> findByUsername(String username);
    // UserService -> UserDetailsService (Interface)
    // 유저를 인증을 할 때 id가 아니라 'username'
}
