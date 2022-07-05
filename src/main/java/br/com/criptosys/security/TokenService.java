package br.com.criptosys.security;

import br.com.criptosys.dto.UserTokenDTO;
import br.com.criptosys.dto.UserSS;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(Authentication authentication) {

        UserSS logged = (UserSS) authentication.getPrincipal();


        Date today = new Date();
        Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));


        return Jwts.builder().setIssuer("API criptosys")
                .claim("usuario", new UserTokenDTO(logged))
                .setSubject(logged.getId().toString())
                .setIssuedAt(today)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public BigInteger getUser(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return new BigInteger(claims.getSubject());
        } catch (Exception e) {
            e.printStackTrace();
            return new BigInteger("-1");
        }
    }

    public String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

    public String encondePassord(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public boolean matchesPassord(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder()
                .matches(rawPassword, encodedPassword);
    }
}
