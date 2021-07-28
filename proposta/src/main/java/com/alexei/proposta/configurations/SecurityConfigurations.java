package com.alexei.proposta.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
    /**
     * Habilita Security para produção.
     */

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.cors()
    //     .and()
    //         .authorizeRequests()
    //             .anyRequest()
    //                 .authenticated()
    //     .and()
    //         .oauth2ResourceServer().jwt();
    // }

    /**
     * Para desenvolvimento desabilita o security.
     */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
    
}
