package com.hhfactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConifg extends WebSecurityConfigurerAdapter {
	//ここにもらったSpring本のセキュリティ部分を記載する
	@Configuration
	static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter{
		@Autowired
		UserDatailsService userDetailsService;
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}
		
	}
}