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
			"/Trailer/**",
			"/videos/**",
			"/Videos/**",
			"/forgot_password/**",
			"/reset_password"
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
				.requestMatchers("/video/**").hasAuthority("USER")
				.requestMatchers("/trailer/**").hasAuthority("USER")
				.requestMatchers("/event/**").hasAuthority("ADMIN")
				.requestMatchers("/view/**").hasAuthority("ADMIN")
				.requestMatchers("/anime/**").hasAuthority("ADMIN")
				.requestMatchers("/viewAnime/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				).formLogin(
							form -> form
									.loginPage("/login")
									.loginProcessingUrl("/login")
									.defaultSuccessUrl("/successHandler")
										
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
