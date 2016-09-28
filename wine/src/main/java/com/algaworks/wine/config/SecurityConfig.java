package com.algaworks.wine.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("joao")
			.password("joao")
			.roles("CADASTRAR_VINHO")
			 .and()
			 .withUser("maria")
			 .password("maria")
			 .roles("CADASTRAR_VINHO", "LISTAR_VINHO");

		auth.ldapAuthentication()
			 .userDnPatterns("uid={0},dc=example,dc=com")
	         .userSearchFilter("uid={0}")
	         .userSearchBase("dc=example,dc=com")
	         .groupSearchBase("dc=example,dc=com")
	         .groupSearchFilter("ou={0}")
	         .contextSource()
	         .url("ldap://ldap.forumsys.com")
	         .managerDn("cn=read-only-admin,dc=example,dc=com")
	         .managerPassword("password").port(389).and();
	
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//			.antMatchers("/vinhos/novo").hasRole("CADASTRAR_VINHO")
//			.antMatchers("/vinhos/**").hasRole("LISTAR_VINHO")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
	}
}
