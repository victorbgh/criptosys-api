package br.com.criptosys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredenciaisDTO {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
}
