package br.com.criptosys.dto;

import lombok.Data;

@Data
public class UserTokenDTO {

    private final Integer id;
    private final String cpf;

    public UserTokenDTO(UserSS usuario) {
        this.id = usuario.getId();
        this.cpf = usuario.getUsername();
    }
}
