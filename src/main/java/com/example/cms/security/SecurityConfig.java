package com.example.cms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

	private CustomUserDetailsService userDetailsService;

	@Bean //this method for encryp our password
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);//y bcrypt? its more secure and widely used //hashing has to be performed 
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {//httpSecurity help us to build security filter object
		return httpSecurity.csrf(csrf->csrf.disable())							
				.authorizeHttpRequests(auth->auth.requestMatchers("/users/register")
						.permitAll()//permit all the user
						.anyRequest()
						.authenticated())
				.formLogin(Customizer
						.withDefaults())
				.build(); 
	}


	//on which user we are providing password encrypt
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();//because its a DB configuration
		provider.setPasswordEncoder(passwordEncoder());//this algorithm will encrypt our password
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}
