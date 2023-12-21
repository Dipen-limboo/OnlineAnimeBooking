package com.springbootAnmte.animte.Controller;


import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootAnmte.animte.ExceptionHandler.UserNotFoundException;
import com.springbootAnmte.animte.Utility.Utility;
import com.springbootAnmte.animte.entity.User;
import com.springbootAnmte.animte.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;
     
    @Autowired
    private UserService userService;
    
     
    public ForgotPasswordController(JavaMailSender mailSender, UserService userService) {
		super();
		this.mailSender = mailSender;
		this.userService = userService;
	}

	@GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
    	return "forgotPassowrd_form";
    }
 
    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model ) throws UnsupportedEncodingException, MessagingException {
    	String mail =  request.getParameter("email");
    	String token = RandomString.make(30);
    	
    	try {
    		userService.updateResetPasswordToken(token, mail);
    		String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token="+token; 
    		sendEmail(mail, resetPasswordLink);
    		model.addAttribute("message", "We have sent a reset password link to your email. Please check..");
    	} catch(UserNotFoundException e) {
    		model.addAttribute("error",  e.getMessage());
    	}
    	return "forgotPassowrd_form";
    }
     
    public void sendEmail(String mail, String link) throws UnsupportedEncodingException, MessagingException {
    	MimeMessage message = mailSender.createMimeMessage(); 
    	MimeMessageHelper mhelper = new MimeMessageHelper(message);
    	
    	mhelper.setFrom("dipenlimboo564@gmail.com", "Limbo Dipen");
    	
    	mhelper.setTo(mail);
    	
    	String subject = "Here's the Link to Reset Your Password ";
    	
    	String content = " <p> Hello" + mail + " </p>"
    			+"<p>You have requested to reset your passowrd</p>"
    			+"<p>Click the link below to change your password</p>"
    			+"<p><a href=\"" +link+ "\"> Change your passowrd</p>"
    			+"<br>"
			    + "<p>Ignore this email if you do remember your password, "
	            + "or you have not made the request.</p>";

    	mhelper.setSubject(subject);
    	mhelper.setText(content, true);
    	mailSender.send(message);
    }  
     
     
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value="token") String token, Model model ) {
    	User user = userService.getByResetPasswordToken(token);
    	model.addAttribute("token", token);
    	
    	if(user == null) {
    		model.addAttribute("message", "invalid token");
    		return "message";
    	}
    	return "resetPassword_form";
    }
     
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model ) {
    	String token = request.getParameter("token");
    	String password = request.getParameter("password");
    	
    	User user = userService.getByResetPasswordToken(token);
    	model.addAttribute("title", "Reset your password");
    	
    	if (user == null) {
    		model.addAttribute("message", "Invalid Token");
    		return "message";
    	} else {
    		userService.updatePassword(user, password);
    		model.addAttribute("body","You have succesfully changed Your password.");
    	}
    	
    	return "redirect:/login";
    }
}
