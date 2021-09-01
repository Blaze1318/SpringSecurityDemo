package com.example.springsecurity.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurity.roles.Role;
import com.example.springsecurity.roles.RoleRepository;

@Service @Transactional
public class UserService implements UserDetailsService{
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	@Autowired
	public UserService(UserRepository userRepository,RoleRepository roleRepository)
	{
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public User saveUser(User user)
	{
		return userRepository.save(user);
	}
	
	public Role saveRole(Role role)
	{
		return roleRepository.save(role);
	}
	
	public void addRoleToUser(String username, String name)
	{
		User user = userRepository.findUserByUsername(username);
		Role role = roleRepository.findRoleByName(name);
		user.getRoles().add(role);
	}
	
	public User getUser(String username)
	{
		return userRepository.findUserByUsername(username);
	}
	
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}

	
	/**
	 * This makes sure a user who exist has simple permissions
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("No user with this username exist");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	}
	
}