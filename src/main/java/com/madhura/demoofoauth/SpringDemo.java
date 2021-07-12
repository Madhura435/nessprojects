package com.madhura.demoofoauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SpringDemo extends WebSecurityConfigurerAdapter {
@Override
protected void configure(HttpSecurity http) throws Exception {
	
	http
	.authorizeRequests(au -> au
		.antMatchers("/", "/error", "/webjars/**").permitAll()
		.anyRequest().authenticated()
	)
	.exceptionHandling(er -> er
		.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
	)
	.csrf(cs -> cs
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	)
	.logout(lo -> lo
		.logoutSuccessUrl("/").permitAll()
	)
	.oauth2Login();
	

}
}
