package com.springbootAnmte.animte.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableMethodSecurity 
public class UserConfiguration {

	@Autowired
	private UserDetailsService userdet;
	
	public static final String[] ENDPOINTS_WHITELIST = {
			"/register/**",
			"/CSS/**",
			"/Images/**",
			"/images/**",
			"/home",
			"/eventpanel",
			"/video",
			"/login",
			"/trailer/**",
			"/Trailer/**"
	};
	
	public static final String[] USER_WHITELIST = {
		"/ticket/**",
		"/video/**"
	};
	
	public static final String[] ADMIN_WHITELIST = {
		"/event",
		"/view",
		"/event/edit/{id}",
		"event/{id}",
		"/anime",
		"/viewAnime",
		"/videos/**",
		"/Videos/**",
		"/anime/edit/{id}",
		"anime/{id}"
	};
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests((authorize) ->
			authorize.requestMatchers(ENDPOINTS_WHITELIST).permitAll()
				.requestMatchers(USER_WHITELIST).hasRole("USER")
				.requestMatchers(ADMIN_WHITELIST).hasRole("ADMIN")
				).formLogin(
							form -> form
									.loginPage("/login")
									.loginProcessingUrl("/login")
									.defaultSuccessUrl("/home")
										
									.permitAll()
									).logout(
											logout -> logout
													.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
													.permitAll()
											);
		return http.build();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userdet)
                .passwordEncoder(passwordEncoder());
    }
}
