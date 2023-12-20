package com.springbootAnmte.animte.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootAnmte.animte.entity.Role;
import com.springbootAnmte.animte.entity.User;
import com.springbootAnmte.animte.repository.RoleRepository;
import com.springbootAnmte.animte.repository.UserRepository;
import com.springbootAnmte.animte.service.UserService;

import jakarta.validation.Valid;

@Service
public class UserServiceImplementation implements UserService{

	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private PasswordEncoder passwordEncoder;

	public UserServiceImplementation(UserRepository userRepo, RoleRepository roleRepo,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public User findUserByEmail(String email) {
		System.out.println(userRepo.findByEmail(email));
		return userRepo.findByEmail(email);
	}


	@Override
	public void saveUser(@Valid User user) {
	    User usr = new User();
	    usr.setUsername(user.getUsername());
	    usr.setEmail(user.getEmail());
	    usr.setPassword(passwordEncoder.encode(user.getPassword()));
	    usr.setPhone(user.getPhone());

	    Role userRole = roleRepo.findByRole("USER");
	    if (userRole == null) {
	        userRole = createAndSaveRole("USER");
	    }
	    usr.setRoles(Arrays.asList(userRole));

	    userRepo.save(usr);
	}

	@Override
	public void saveAdmin(@Valid User admin) {
	    User adminUser = new User();
	    adminUser.setUsername(admin.getUsername());
	    adminUser.setEmail(admin.getEmail());
	    adminUser.setPassword(passwordEncoder.encode(admin.getPassword()));
	    adminUser.setPhone(admin.getPhone());

	    Role adminRole = roleRepo.findByRole("ADMIN");
	    if (adminRole == null) {
	        adminRole = createAndSaveRole("ADMIN");
	    }
	    adminUser.setRoles(Arrays.asList(adminRole));

	    userRepo.save(adminUser);
	}

	private Role createAndSaveRole(String roleName) {
	    Role newRole = new Role();
	    newRole.setRole(roleName);
	    return roleRepo.save(newRole);
	}


	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

}
