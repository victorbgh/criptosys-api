package br.com.criptosys.service;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserService(userRepository, bCryptPasswordEncoder);
    }

    @Test
    public void saveUser(){
        UserDE user = gerarUserDE();
        Mockito.when(userRepository.save(user))
                .thenReturn(user);

        userService.save(user);
        Mockito.verify(userRepository).save(user);
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
