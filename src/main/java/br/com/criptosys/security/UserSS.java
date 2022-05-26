package br.com.criptosys.security;

import br.com.criptosys.enums.Profile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserSS implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(Integer id, String email, String password, Set<Profile> profile) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profile != null ? profile.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList()) : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Profile profile) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
    }
}
