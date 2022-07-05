package br.com.criptosys.security;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.dto.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private br.com.criptosys.service.UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSS user = new UserSS();
        UserDE userFromDB = userService.findByUsername(username);
        if (userFromDB != null) {
            user.setId(userFromDB.getUserId().intValue());
            user.setEmail(userFromDB.getEmail());
            user.setPassword(userFromDB.getPassword());
        }

        if (user.getId() != null) {
            return user;
        }

        throw new UsernameNotFoundException("Invalid Data!");
    }

}