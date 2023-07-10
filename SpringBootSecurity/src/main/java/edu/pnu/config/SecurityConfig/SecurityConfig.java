package edu.pnu.config.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

import edu.pnu.Service.BoardOAuth2UserDetailsService;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	@Autowired
	BoardOAuth2UserDetailsService oauthService;
	@Bean
	public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());

		http.authorizeHttpRequests(security -> {
			security.requestMatchers("/member/**")
			        .authenticated()
				    .requestMatchers("/manager/**")
					.hasAnyAuthority("MANAGER", "ADMIN")
					.requestMatchers("/admin/**")
					.hasRole("ADMIN")
					.anyRequest()
					.permitAll();

		});
		http.formLogin(formLogin -> {
			formLogin.loginPage("/login")
			         .defaultSuccessUrl("/loginSuccess", true);
		});
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		http.logout(logt->{
			logt.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login");
		});
		
		http.oauth2Login(oauth2->{
			oauth2.loginPage("/login");
		});


		http.oauth2Login(oauth2->{
			oauth2.loginPage("/login")
				.userInfoEndpoint(uend->uend.userService(oauthService))
				.defaultSuccessUrl("/loginSuccess", true);
		});

		

		return http.build();

	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();

	}
	

}
