package com.springbootAnmte.animte.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootAnmte.animte.entity.User;
import com.springbootAnmte.animte.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private UserService userService;
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

    @GetMapping("/login")
    public String login(){
        return "login";
    }
	
	@GetMapping("/register")
	public String registration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register_panel";
	}

	@PostMapping("/register/save")
	public String savingNewUser(
			@Valid 
			@ModelAttribute("user") User user,
			BindingResult result,
			Model model) {		
		User existingUser =  userService.findUserByEmail(user.getEmail());
		if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty())
		{
			result.rejectValue("email", null, "There is already an account registered with the same email ");
		}	
		if(result.hasErrors()) {
			model.addAttribute("userError", user);
			return "/register";
		}
		userService.saveUser(user);
		return "redirect:/login?sucess";
	}
	@GetMapping("/users")
	public String users(Model model) {
		List <User> users = userService.findAllUsers();
		model.addAttribute("userList", users);
		return "userPanel";
	}
	
	
}
