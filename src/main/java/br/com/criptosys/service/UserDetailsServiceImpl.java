package br.com.criptosys.service;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.domain.repository.UserRepository;
import br.com.criptosys.dto.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDE userDE = this.userRepository.findByEmail(email);
        if(userDE == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(userDE.getUserId().intValue(), userDE.getEmail(), userDE.getPassword(), null);
    }
}
