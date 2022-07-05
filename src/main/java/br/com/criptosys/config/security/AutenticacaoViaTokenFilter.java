package br.com.criptosys.config.security;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.dto.UserSS;
import br.com.criptosys.security.TokenService;
import br.com.criptosys.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserService userService;

    HttpServletRequest webRequest;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenService.recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);

        if (valido) {
            autenticarCliente(request, token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(HttpServletRequest request, String token) {
        UserSS user = new UserSS();

        BigInteger id = tokenService.getUser(token);

        UserDE userDB = userService.findById(id).get();
        user = new UserSS(userDB.getUserId().intValue(), userDB.getEmail(), userDB.getPassword(), null);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
