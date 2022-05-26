package br.com.criptosys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {};

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/api/v1/user"
    };

//    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
//            http.headers().frameOptions().disable();
//        }
//
//        http.cors().and().csrf().disable();
//        http.authorizeRequests().
//                antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll().
//                antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().
//                antMatchers(PUBLIC_MATCHERS).permitAll().
//                anyRequest().authenticated();
//        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
//        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailService));
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST"
                , "GET"
                , "PUT"
                , "DELETE"
                , "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs"
                        , "/configuration/ui"
                        , "/swagger-resources/**"
                        , "/configuration/**"
                        , "/swagger-ui.html"
                        , "/webjars/**");
    }
}
