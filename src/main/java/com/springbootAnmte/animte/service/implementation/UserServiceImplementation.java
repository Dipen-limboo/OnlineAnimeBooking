package com.springbootAnmte.animte.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootAnmte.animte.ExceptionHandler.UserNotFoundException;
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


	@Override
	public Long findIdByEmail(String email) {
		User user = userRepo.findByEmail(email);
		return (user!=null)?user.getUserId():null;
	}


	@Override
	public User getUserById(Long userId) {
		
		return userRepo.findById(userId).get();
	}


	@Override
	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException{
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(email);
		
		if(user != null) {
			user.setResetPasswordToken(token);
			userRepo.save(user);
		} else {
			throw new UserNotFoundException("Could not found any user with an email " + email);
		}
	}
	
	


	@Override
	public User getByResetPasswordToken(String token) {
		return userRepo.findByResetPasswordToken(token);
	}


	@Override
	public void updatePassword(User user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);
		user.setResetPasswordToken(null);
		userRepo.save(user);
	}

}
