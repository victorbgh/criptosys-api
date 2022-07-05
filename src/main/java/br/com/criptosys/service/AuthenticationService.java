package br.com.criptosys.service;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.dto.PasswordRecoveryDTO;
import br.com.criptosys.shared.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserService userService;

    @Autowired
    public AuthenticationService(UserService userService){
        this.userService = userService;
    }

    public void passwordRecovery(PasswordRecoveryDTO passwordRecoveryDTO){
        UserDE user = this.userService.findByEmailAndUsername(passwordRecoveryDTO.getEmail(), passwordRecoveryDTO.getUsername());
        if(user == null){
            throw new UserNotFoundException("User not found");
        }


    }
}
