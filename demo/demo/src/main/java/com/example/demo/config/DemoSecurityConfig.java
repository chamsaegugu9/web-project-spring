package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.filter.DemoJwtAuthenticationFilter;
import com.example.demo.filter.DemoLoginFilter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Bean
	public AuthenticationManager authenticationManager()
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder encoderPWD() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // 허용할 출처
		corsConfig.setAllowedMethods(Arrays.asList("*")); // 허용할 HTTP 메서드
		corsConfig.setAllowedHeaders(Arrays.asList("*")); // 허용할 헤더
		corsConfig.setAllowCredentials(true); // 자격 증명(Credentials) 허용 여부

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig); // 모든 경로에 CORS 설정

		return source;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/auth/**").authenticated()
						.anyRequest().permitAll())
				.sessionManagement((sessionManagement) -> {
					sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.csrf((csrf) -> {
					csrf.disable();
				})
				.cors((cors) -> {
					cors.configurationSource(corsConfigurationSource());
				})
				.httpBasic((httpBasic) -> {
					httpBasic.disable();
				})
				.formLogin((formLogin) -> {
					formLogin.disable();
				})
				.addFilterBefore(new DemoJwtAuthenticationFilter(authenticationManager()),
						BasicAuthenticationFilter.class)
				.addFilterBefore(new DemoLoginFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
