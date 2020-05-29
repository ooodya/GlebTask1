package com.zaycevImaginaryCompany.glebTask1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zaycevImaginaryCompany.glebTask1.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() 
	{
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
	private UserService uService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
			.antMatchers("/", "/register", "/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login").defaultSuccessUrl("/userAccounts", true)
			.permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(uService).passwordEncoder(bCryptPasswordEncoder());
	}
	
}
