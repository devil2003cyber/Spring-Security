//package com.example.helloWorld.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableMethodSecurity
//public class SecurityConfig {
//
//	
//	private JwtAuthenticationFilter jwtAuthFilter;
//	private AuthenticationProvider authenticationProvider;
//	
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		
//		http
//        .csrf(csrf -> csrf.disable()) // Disable CSRF protection
//        .authorizeHttpRequests(auth -> auth
//            .requestMatchers("/api/v1/auth/**").permitAll() // Example of permitting all requests to a specific path
//            .anyRequest().authenticated() // All other requests require authentication
//        )
//        .sessionManagement(session -> session
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session management
//        )
//        .authenticationProvider(authenticationProvider) // Set the custom authentication provider
//        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add the custom JWT filter before the username/password authentication filter
//
//    return http.build();
//		
//	}
//}
package com.example.helloWorld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.example.helloWorld.user.permission.ADMIN_CREATE;
import static com.example.helloWorld.user.permission.ADMIN_DELETE;
import static com.example.helloWorld.user.permission.ADMIN_READ;
import static com.example.helloWorld.user.permission.ADMIN_UPDATE;
import static com.example.helloWorld.user.permission.MANAGER_CREATE;
import static com.example.helloWorld.user.permission.MANAGER_DELETE;
import static com.example.helloWorld.user.permission.MANAGER_READ;
import static com.example.helloWorld.user.permission.MANAGER_UPDATE;
import static com.example.helloWorld.user.Role.ADMIN;

import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.DELETE;;
@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {


	
	
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/authenticate", "/api/v1/auth/register").permitAll() // Example of permitting all requests to a specific path
                .anyRequest().authenticated() // All other requests require authentication
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session management
            )
            .authenticationProvider(authenticationProvider) // Set the custom authentication provider
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add the custom JWT filter before the username/password authentication filter

        return http.build();
    }
}
