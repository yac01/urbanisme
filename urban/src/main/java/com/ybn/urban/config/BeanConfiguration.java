package com.ybn.urban.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public KeyProvider rkp() {
        return new KeyProvider();
    }
    @Bean
    @Qualifier("exceptionRes")
    public String [] exceptionRes() {
        return new String[] {"global.yml"};
    }
    @Bean
    Algorithm algorithm() {
        return Algorithm.RSA256(rkp());
    }
    @Bean
    JWTVerifier jwtVerifier() {
        return JWT.require(algorithm())
                .build();
    }
    @Bean
    AntPathMatcher matcher() {
        return new AntPathMatcher();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
}
