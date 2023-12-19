
package com.springbootAnmte.animte.entity;
 
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table; 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank; 
import
jakarta.validation.constraints.Pattern;
 
 @Entity
 @Table(name = "users") 
 public class User {
  
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private long userId;
	 
	 @Column(name ="username")
	 @NotBlank(message = "Username is required!!") 
	 private String username;
	
	
	 @Column(name ="password")
	 private String password;
	  

	 private String ConfirmPassword;
	 
	 @Column(name = "email")
	 @Email 
	 private String email;
	 
	 @Column(name= "phone")
	 @Pattern(regexp="^(98|97)\\d{8}$",
	 message="{The phone number must be number start with 97 or 98, it must contains 10 number}"
	 )
	 private String phone;
	 
	 @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	 @JoinTable(
	         name="users_roles",
	         joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USERID")},
	         inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	 private List<Role> roles = new ArrayList<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(long user_id, @NotBlank(message = "Username is required!!") String username,
			String password,
			String ConfirmPassword, @Email String email,
			@Pattern(regexp = "^(98|97)\\d{8}$", message = "{The phone number must be number start with 97 or 98, it must contains 10 number}") String phone,
			List<com.springbootAnmte.animte.entity.Role> roles) {
		super();
		this.userId = user_id;
		this.username = username;
		this.password = password;
		this.ConfirmPassword = ConfirmPassword;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long user_id) {
		this.userId = user_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
		
	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
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
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
 