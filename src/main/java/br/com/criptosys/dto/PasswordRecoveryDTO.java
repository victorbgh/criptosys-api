package br.com.criptosys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PasswordRecoveryDTO {

    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String username;

}
