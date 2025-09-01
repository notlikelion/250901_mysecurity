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
