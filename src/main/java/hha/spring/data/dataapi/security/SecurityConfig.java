package hha.spring.data.dataapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hha.spring.data.dataapi.service.UserService;
import hha.spring.data.dataapi.security.JwtFilter;;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService service;
	@Autowired
    private JwtFilter jwtFilter;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		String whiteList[] = {"/login", "/register"};
		
		// Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();
        
        // Set session management to stateless
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        
        // Set unauthorized requests exception handler
        http = http.exceptionHandling().and();
        http.authorizeRequests()
        	.antMatchers(HttpMethod.GET, "/products").permitAll()
        	.antMatchers(whiteList).permitAll()
	        .antMatchers(HttpMethod.GET, "/categories").permitAll()
	        .antMatchers(HttpMethod.GET, "/users").permitAll()
	        .antMatchers(HttpMethod.POST, "/users").permitAll()
	        .anyRequest().authenticated();
        
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		 authenticationManagerBuilder.userDetailsService(service);
	}
	
	@Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
