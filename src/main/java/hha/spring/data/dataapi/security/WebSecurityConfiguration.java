package hha.spring.data.dataapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is to manage chain of filter and add the token checking before specified controllers
 */


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Entry points
        http.authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers("/api/customer/**").permitAll()
                .antMatchers("/api/admin/signin/**").permitAll()
                .antMatchers("/api/admin/signup/**").permitAll()
                .antMatchers("/api/categories/**").permitAll()
                .antMatchers("/api/v2/**").permitAll()
                .antMatchers("/api/weighttype/**").permitAll()
                .antMatchers("/api/admin/users/**").access("hasRole('ADMIN')")
                .antMatchers("/api/admin/product/**").access("hasRole('ADMIN')")
                .antMatchers("/api/admin/report/**").access("hasRole('ADMIN')")
                .antMatchers("/api/admin/order/**").access("hasRole('ADMIN')")
                //Disallow everthing else..
                .anyRequest().authenticated();

        //Disable CSRF(cross site request forgery)
        http.cors().and().csrf().disable();

        //No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new JwtFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);

    }


}
