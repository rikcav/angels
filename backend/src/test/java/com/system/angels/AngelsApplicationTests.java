package com.system.angels;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class AngelsApplicationTests {
    @MockBean
    private JavaMailSender javaMailSender; // Mock the missing bean

    @Test
    void contextLoads() {
    }

}
