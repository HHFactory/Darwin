package com.hhfactory.mapper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hhfactory.dto.UserDto;
import com.hhfactory.entity.UserEntity;

public class ToUserEntityMapperConfig {
	@Autowired
	private UserDto userDto;
	@Autowired
	private UserEntity userEntity;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	/**
	 * UserDtoからパスワードをハッシュ化する
	 * @param address[String]:住所
	 * @return ハッシュ化した数値
	 * @throws IOException 
	 */
	public String passwordencode(String password){
		return passwordEncoder.encode(password);	
	}
}