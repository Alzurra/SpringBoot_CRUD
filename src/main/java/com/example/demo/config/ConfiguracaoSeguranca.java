package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MeuUserDetailsService meuUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(meuUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		
		http.
			antMatcher("/**").
				authorizeRequests().
					antMatchers("/exemplo").
						permitAll().
				antMatchers("/admin/**").
					hasAnyAuthority("ADMIN").
				anyRequest().
					authenticated().
				and().
					formLogin();
		
						/*permitAll().
						loginPage("/login").
						usernameParameter("user").
						passwordParameter("senha").
						defaultSuccessUrl("/admin").
				and().
					logout().
						permitAll().*/
							
	}
}
