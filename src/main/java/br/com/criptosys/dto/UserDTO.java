package br.com.criptosys.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
@Builder
public class UserDTO {

    private BigInteger userId;

    private String name;

    private String email;

    private String username;

    private String password;

    private Boolean active;

}
