package br.com.criptosys.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class CredenciaisDTO {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken mapToAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
