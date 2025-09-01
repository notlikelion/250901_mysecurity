package com.example.mysecurity.service;

import com.example.mysecurity.domain.UserAccount;
import com.example.mysecurity.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    // 생성자 주입

    @Transactional
    public Long register(String username, String password) {
        if (userAccountRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다");
        } // 쓰기 - 은근히 자원을 많이 먹음 -> insert 작업 자체를 막음
        // 이름은 안겹친다
        // JPA DB 쓰던 것과 같은 과정인데...
        // password를 저장할 때 로그인할 때 쓰는 passwordEncoder로 인코딩해서 저장하는 과정이 추가
        String encodedPassword = passwordEncoder.encode(password);
        // DB가 털릴 수 있다는 전제로 모든 건 작성이 된다 -> 알아볼 수는...
        UserAccount userAccount = UserAccount.builder()
                .username(username)
//                .password(passwordEncoder.encode(password))
                .password(encodedPassword)
                .role("USER") // 기본 역할만 부여
                .build();
        return userAccountRepository.save(userAccount).getId();
    }
}