package br.com.criptosys.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CUSTOMER(2, "ROLE_CUSTOMER");

    private final int cod;
    private final String description;

    private Profile(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public static Profile toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(Profile x : Profile.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + cod);
    }
}
