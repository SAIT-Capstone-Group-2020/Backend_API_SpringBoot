package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Users;
import hha.spring.data.dataapi.security.LoginDto;
import hha.spring.data.dataapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	//It needs more logic and need to check the error exception
	@PostMapping("/api/admin/signin")
	public String login(@RequestBody LoginDto loginDto) {

		//go to the sign in process and recieve the token if there is no problem.
		String token = service.adminSignIn(loginDto.getEmail(), loginDto.getPassword());

		if(token == "") {
			new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed");
		}
		return token;
	}

	@PostMapping("/api/admin/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public Users signup(@RequestBody LoginDto loginDto) {
		System.out.println(loginDto.getEmail()+loginDto.getPassword()+loginDto.getUser_name());

		Users user = service.adminSignUP(loginDto.getEmail(), loginDto.getPassword(), loginDto.getUser_name());

		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User already exists");
		}

		return user;
	}

	@PostMapping("/api/admin/activate")
	public Users activateAdmin(@RequestParam("uuid") String uuid) {

		Users user = service.adminActivate(uuid);

		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User is not exists");
		}

		return user;
	}

}
