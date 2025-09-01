package com.example.mysecurity.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="users") // user? -> user, member -> 이미 쓰고 있을 수 있음
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true) // 로그인할 때 쓰는 실질적 ID
    private String username;
    @Column(nullable = false)
    private String password;
    // 권한
    private String role; // USER, ADMIN

    @Builder
    public UserAccount(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}