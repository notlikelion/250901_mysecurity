package com.example.mysecurity.service;

import com.example.mysecurity.domain.UserAccount;
import com.example.mysecurity.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
// UserDetailsService -> Spring Security에서 저장하는 인증 관련 서비스 인터페이스
// 유저의 데이터를 불러올 수 있는 기능을 연결해서 유저 인증정보 (User Detail) -> User
// User -> ~~~~~~~.User. / UserAccount -> 안겹치면 편하다...
// UserDetailsService -> 의존성 주입 -> POST /login (Spring Security에서의 내장)
public class UserService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    // -> loadUserByUsername ????
    // 디폴트 폼, 커스텀 폼 -> POST /login -> [password], username
    // username -> password를 꺼내줌
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
        // UserDetails -> password 비교 최종 인증???.
        return new User(userAccount.getUsername(),
                userAccount.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userAccount.getRole())));
    }
}
