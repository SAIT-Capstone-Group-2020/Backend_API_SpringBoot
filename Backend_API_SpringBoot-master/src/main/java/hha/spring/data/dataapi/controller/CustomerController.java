package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Users;
import hha.spring.data.dataapi.security.LoginDto;
import hha.spring.data.dataapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.HttpServerErrorException;

@RestController
public class CustomerController {

	@Autowired
	private UserService service;

	//It needs more logic and need to check the error exception
	@PostMapping("/api/customer/signIn")
	public String login(@RequestBody LoginDto loginDto) {

		//go to the sign in process and receive the token if there is no problem.
		String token = service.customerSignIn(loginDto.getUsername(), loginDto.getPassword());

		if(token == "") {
			new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed");
		}
		return token;
	}

	@PostMapping("/api/customer/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public Users signup(@RequestBody LoginDto loginDto) {
		Users user = service.customerSignUP(loginDto.getUsername(), loginDto.getPassword());

		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User already exists");
		}

		return user;
	}

	/*
	@GetMapping
	@PreAuthorize("hasRole('customer')")
	public List<Users> getAllProducts() {

		return service.getAll();
	}*/

}
