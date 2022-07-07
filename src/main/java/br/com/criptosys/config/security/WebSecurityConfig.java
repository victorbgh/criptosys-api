package br.com.criptosys.config.security;

import br.com.criptosys.security.AutenticacaoService;
import br.com.criptosys.security.TokenService;
import br.com.criptosys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
//            "/swagger-resources/**",
//            "/webjars/**",

    };

    private static final String[] PUBLIC_MATCHERS_GET = {};

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/api/v1/user",
            "/api/v1/auth/**"
    };

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable();
        http.authorizeRequests().
                antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll().
                antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().
                antMatchers(PUBLIC_MATCHERS).permitAll().
                anyRequest().authenticated()
        .and().cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AuthenticationTokenFilter(tokenService, userService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html"
                , "/v3/api-docs/**"
                , "/webjars/**"
                , "/configuration/**"
                , "/swagger-resources/**"
                , "/swagger-ui/**"
                , "/h2-console/**");
    }
}
