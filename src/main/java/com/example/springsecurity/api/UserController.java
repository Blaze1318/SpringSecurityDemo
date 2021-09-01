package com.example.springsecurity.api;

import java.net.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springsecurity.roles.Role;
import com.example.springsecurity.users.User;
import com.example.springsecurity.users.UserService;


@RestController
@RequestMapping(path = "api")
@CrossOrigin(origins = "*")
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(path = "users")
	public ResponseEntity<List<User>>getUsers()
	{
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@PostMapping(path = "users")
	public ResponseEntity<User> saveUser(@RequestBody User user)
	{
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
		return ResponseEntity.created(uri).body(userService.saveUser(user));
	}
	
	@PostMapping(path = "roles")
	public ResponseEntity<Role> saveRole(@RequestBody Role role)
	{
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}
	
	@PostMapping(path = "roles/addtouser")
	public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form)
	{
		userService.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.ok().build();
	}
	
}

class RoleToUserForm{
	private String username;
	private String roleName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "RoleToUserForm [username=" + username + ", roleName=" + roleName + "]";
	}
	
	
	
}
