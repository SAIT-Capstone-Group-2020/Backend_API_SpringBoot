package hha.spring.data.dataapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hha.spring.data.dataapi.domain.User;
import hha.spring.data.dataapi.security.AuthRequest;
import hha.spring.data.dataapi.security.JwtUtil;
import hha.spring.data.dataapi.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
    private JwtUtil jwtUtil;
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public void register (@RequestBody User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		service.saveUser(user);
	}
	
	@PostMapping("/login")
	public String login (@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}
}
