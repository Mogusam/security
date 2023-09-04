package org.jgmp.security.config;

import org.jgmp.security.service.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/index", "/about").permitAll()
                .antMatchers("/user").permitAll()
                .antMatchers("/info").hasAuthority("VIEW_INFO")
                .antMatchers("/admin").hasAuthority("VIEW_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                .failureHandler(customAuthenticationFailureHandler)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/about")
                .permitAll();
    }


    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        return authorityMapper;
    }


/** =-=-=-=-=   for TASK 1 =-=-=-=-=-=-
 @Bean
 @Override public UserDetailsService userDetailsService() {
 UserDetails user =
 User.withDefaultPasswordEncoder()
 .username("email@org.ua")
 .password("password")
 .roles("USER")
 .build();
 return new InMemoryUserDetailsManager(user);
 }
 */
}
