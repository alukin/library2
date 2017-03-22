/*
 * 
 * 
 */
package ua.cn.al.teach.library2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.cn.al.teach.library2.auth.NotAuthenticatedEntryPoint;
import ua.cn.al.teach.library2.auth.TokenSecurityFilter;

/**
 *
 * @author al
 */
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private NotAuthenticatedEntryPoint unauthorizedHandler;
    
    @Bean
    public TokenSecurityFilter authenticationTokenFilterBean() throws Exception {
        return new TokenSecurityFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }
}
