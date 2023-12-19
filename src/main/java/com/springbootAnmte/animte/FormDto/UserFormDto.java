package com.springbootAnmte.animte.FormDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserFormDto {

    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String phone;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    
    @NotEmpty(message = "Confirm Password should not be empty")
    private String confirmPassword;

	public UserFormDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserFormDto(Long id, @NotEmpty String username, @NotEmpty String email,
			@NotEmpty(message = "Email should not be empty") @Email String phone,
			@NotEmpty(message = "Password should not be empty") String password,
			@NotEmpty(message = "Confirm Password should not be empty") String confirmPassword) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
    
    

}
