package com.example.mysecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http...
        http.
                // authz, auth..
            authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register").permitAll()
                .requestMatchers("/mypage").hasRole("USER")
                .anyRequest().authenticated() // 로그인된 유저한테만 허용
                )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
            )
            .logout(logout -> logout
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true))
            .exceptionHandling(exception -> exception.accessDeniedPage("/access-denied"))
        ;
        return http.build();
    }
}
