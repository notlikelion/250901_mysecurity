package com.example.mysecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean : 스프링컨테이너가 관리하는 객체
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        // BCrypt를 꼭 쓰겠다
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // 지금까지의 알고리즘을 일단 표시를 하고, 다음에 최신 알고리즘이 다른 거라면 또 그걸 쓰겠다는 것
    }

    // 미리 작성하는 이유 -> PasswordEncoder -> 저장할 때와 로그인할 때
}
