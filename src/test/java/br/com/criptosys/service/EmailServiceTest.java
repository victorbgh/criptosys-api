package br.com.criptosys.service;

import br.com.criptosys.domain.entity.UserDE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sendEmail(){
        UserDE user = gerarUserDE();
        emailService.sendNewPasswordEmail(user, "123456");
        Mockito.verify(emailService).sendNewPasswordEmail(user, "123456");
    }

    public UserDE gerarUserDE(){
        return UserDE.builder()
                .userId(new BigInteger("1"))
                .name("victor")
                .email("victor@gmail.com")
                .username("victorhgbb")
                .password("123456")
                .active(true)
                .build();
    }
}
