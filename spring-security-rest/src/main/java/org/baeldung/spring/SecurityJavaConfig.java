package org.nklkarthi.spring;

import org.nklkarthi.security.MySavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("org.nklkarthi.security")
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    // @Autowired
    // private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityJavaConfig() {
        super();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    //

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("temporary").password("temporary").roles("ADMIN").and().withUser("user").password("userPass").roles("USER");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {// @formatter:off
        http
        .csrf().disable()
        .authorizeRequests()
        .and()
        .exceptionHandling()
//        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
        .antMatchers("/api/csrfAttacker*").permitAll()
        .antMatchers("/api/customer/**").permitAll()
        .antMatchers("/api/foos/**").authenticated()
        .antMatchers("/api/async/**").permitAll()
        .and()
        .httpBasic()
//        .and()
//        .successHandler(authenticationSuccessHandler)
//        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .logout();
    } // @formatter:on

    @Bean
    public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
        return new MySavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

}