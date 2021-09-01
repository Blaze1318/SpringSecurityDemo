package com.example.springsecurity;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springsecurity.roles.Role;
import com.example.springsecurity.users.User;
import com.example.springsecurity.users.UserService;

@SpringBootApplication
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}
	
	/**
	 * This ensures the password encoder can be grabbed from the start of application
	 * @return
	 */
	@Bean
	PasswordEncoder PasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	CommandLineRunner run(UserService userService)
	{
		return args ->{
			userService.saveRole(new Role(null,"HACKER"));
			userService.saveRole(new Role(null,"ENGINEER"));
			userService.saveRole(new Role(null,"BACKEND_DEVELOPER"));
			userService.saveRole(new Role(null,"FRONTEND_DEVELOPER"));
			
			userService.saveUser(new User(null,"Sam","samsmith","12345",new ArrayList<>()));
			userService.saveUser(new User(null,"Sammy","samsmithh","12345",new ArrayList<>()));
			userService.saveUser(new User(null,"Samg","samsmithd","12345",new ArrayList<>()));
			userService.saveUser(new User(null,"Samd","samsmgith","12345",new ArrayList<>()));
			
			userService.addRoleToUser("samsmith", "HACKER");
			userService.addRoleToUser("samsmith", "ENGINEER");
		};
	}
	

}
