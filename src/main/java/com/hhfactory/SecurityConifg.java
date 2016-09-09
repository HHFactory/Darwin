package com.hhfactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConifg extends WebSecurityConfigurerAdapter {
	//ここにもらったSpringのセキュリティ部分を記載する
}
