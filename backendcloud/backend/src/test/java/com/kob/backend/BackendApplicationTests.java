package com.kob.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        tln(passwordEncoder.encode("123123"));
//        tln(passwordEncoder.matches("123123","$2a$10$iq4am0SFTojICTU87r9WTOplH3UTGbocB9TRkuT7yyazwa5kZkrYi"));
//        tln(passwordEncoder.encode("123"));
//        tln(passwordEncoder.encode("33333"));
    }

}
