//package com.example.helloWorld.auth;
//
//import org.apache.catalina.startup.ClassLoaderFactory.Repository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.helloWorld.user.Role;
//import com.example.helloWorld.user.User;
//import com.example.helloWorld.user.UserRepository;
//
//import com.example.helloWorld.config.JwtService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class AuthenticationService {
//	@Autowired
//	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
//	private final JwtService jwtService;
//	private final AuthenticationManager authenticationManager;
//	
//	 
//
//	    
////	public AuthenticationResponse register(RegisterRequest request) {
////		// TODO Auto-generated method stub
////		var user = User.Builder()
////				.firstname(request.getFirstname())
////				.lastname(request.getLastname())
////				.email(request.getEmail())
////				.password(passwordEncoder.encode(request.getPassword()))
////				.role(Role.User)
////				.build();
////		userRepository.save(user);
////		var jwtToken= jwtService.generateToken(user);
////		return AuthenticationResponse.builder()
////				.token(jwtToken)
////				.build();
////	}
//	public AuthenticationResponse register(RegisterRequest request) {
//	    var user = User.builder()
//	            .firstname(request.getFirstname())
//	            .lastname(request.getLastname())
//	            .email(request.getEmail())
//	            .password(passwordEncoder.encode(request.getPassword()))
//	            .role(Role.USER)
//	            .build();
//	    userRepository.save(user);
//	    var jwtToken = jwtService.generateToken(user);
//	    return AuthenticationResponse.builder()
//	            .token(jwtToken)
//	            .build();
//	}
//
////	public AuthenticationResponse authenticate(AuthenticationRequest request) {
////		// TODO Auto-generated method stub
////		authenticationManager.authenticate(
////				new UsernamePasswordAuthenticationToken(
////						request.getEmail(),
////						request.getPassword()
////						)
////				);
////		var user  = userRepository.findByEmail(request.getEmail())
////				.orElseThrow();
////		var jwtToken = jwtService.generateToken(user);
////		return AuthenticationResponse.builder()
////				.token(jwtToken)
////				.build();
////	}
//	  public AuthenticationResponse authenticate(AuthenticationRequest request) {
//	        log.debug("Authenticating user with email: {}", request.getEmail());
//	        try {
//	            authenticationManager.authenticate(
//	                    new UsernamePasswordAuthenticationToken(
//	                            request.getEmail(),
//	                            request.getPassword()
//	                    )
//	            );
//	        } catch (Exception e) {
//	            log.error("Authentication failed for user: {}", request.getEmail(), e);
//	            throw e;
//	        }
//	        var user = userRepository.findByEmail(request.getEmail())
//	                .orElseThrow(() -> new RuntimeException("User not found"));
//	        var jwtToken = jwtService.generateToken(user);
//	        return AuthenticationResponse.builder()
//	                .token(jwtToken)
//	                .build();
//	    }
//
//	
//}

package com.example.helloWorld.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.helloWorld.user.Role;
import com.example.helloWorld.user.User;
import com.example.helloWorld.user.UserRepository;
import com.example.helloWorld.config.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    @Autowired
//    public AuthenticationService(UserRepository userRepository,
//                                 PasswordEncoder passwordEncoder,
//                                 JwtService jwtService,
//                                 AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//        log.debug("UserRepository injected: {}", userRepository != null);
//        log.debug("PasswordEncoder injected: {}", passwordEncoder != null);
//        log.debug("JwtService injected: {}", jwtService != null);
//        log.debug("AuthenticationManager injected: {}", authenticationManager != null);
//    }

    public AuthenticationResponse register(RegisterRequest request) {
        log.debug("Registering user with email: {}", request.getEmail());
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.debug("Authenticating user with email: {}", request.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            log.error("Authentication failed for user: {}", request.getEmail(), e);
            throw e;
        }
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
