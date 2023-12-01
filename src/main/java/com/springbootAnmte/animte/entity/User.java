/*
 * package com.springbootAnmte.animte.entity;
 * 
 * import jakarta.persistence.Column; import jakarta.persistence.Entity; import
 * jakarta.persistence.GeneratedValue; import
 * jakarta.persistence.GenerationType; import jakarta.persistence.Id; import
 * jakarta.persistence.Table; import jakarta.validation.constraints.Email;
 * import jakarta.validation.constraints.NotBlank; import
 * jakarta.validation.constraints.Pattern;
 * 
 * @Entity
 * 
 * @Table(name = "users") public class User {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long user_id;
 * 
 * @Column(name ="username")
 * 
 * @NotBlank(message = "Username is required!!") private String username;
 * 
 * 
 * @Column(name ="password")
 * 
 * @Pattern(regexp=
 * "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$")
 * private String password;
 * 
 * 
 * @Column(name = "email")
 * 
 * @Email private String email;
 * 
 * @Column(name= "phone")
 * 
 * @Pattern(regexp="^(98|97)\\d{8}$",
 * message="{The phone number must be number start with 97 or 98, it must contains 10 number}"
 * ) private String phone;
 * 
 * 
 * public User() { super(); // TODO Auto-generated constructor stub }
 * 
 * public User(@NotBlank(message = "Username is required!!") String username,
 * 
 * @Pattern(regexp =
 * "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$")
 * String password,
 * 
 * @Email String email,
 * 
 * @Pattern(regexp = "^(98|97)\\d{8}$", message =
 * "{The phone number must be number start with 97 or 98, it must contains 10 number}"
 * ) String phone) { super(); this.username = username; this.password =
 * password; this.email = email; this.phone = phone; }
 * 
 * 
 * 
 * public long getUser_id() { return user_id; }
 * 
 * public void setUser_id(long user_id) { this.user_id = user_id; }
 * 
 * public String getUsername() { return username; }
 * 
 * public void setUsername(String username) { this.username = username; }
 * 
 * public String getPassword() { return password; }
 * 
 * public void setPassword(String password) { this.password = password; }
 * 
 * public String getEmail() { return email; }
 * 
 * public void setEmail(String email) { this.email = email; }
 * 
 * public String getPhone() { return phone; }
 * 
 * public void setPhone(String phone) { this.phone = phone; }
 * 
 * 
 * 
 * }
 */