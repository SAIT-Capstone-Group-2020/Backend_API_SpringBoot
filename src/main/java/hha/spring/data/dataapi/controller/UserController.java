package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Message;
import hha.spring.data.dataapi.domain.Users;
import hha.spring.data.dataapi.security.LoginDto;
import hha.spring.data.dataapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/api/admin/signin")
	public Message login(@RequestBody LoginDto loginDto) {

		//go to the sign in process and receive the token if there is no problem.
		String token = service.adminSignIn(loginDto.getEmail(), loginDto.getPassword());

		Message message = new Message("ok", token);

		return message;
	}

	//request body should be json type({email: '', password: '', name: ''})
	@PutMapping("/api/admin/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public Users signup(@RequestBody LoginDto loginDto) {

		Users user = service.adminSignUP(loginDto.getEmail(), loginDto.getPassword(), loginDto.getUser_name());

		if(user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
		}

		return user;
	}

	//need to signin first, and get token
	//how to use token: add 'Authorization' key with 'Bearer ' on the Header.
	//request body should be json type({email: '', password: ''})
	@PutMapping("/api/admin/users/activate")
	public Users activateAdmin(@RequestParam("uuid") String uuid) {

		Users user = service.adminActivate(uuid);

		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User not exists");
		}

		return user;
	}

	@PutMapping("/api/admin/users/deactivate")
	public Users deactivateAdmin(@RequestParam("uuid") String uuid) {

		Users user = service.adminDeActivate(uuid);

		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User not exists");
		}

		return user;
	}


	@GetMapping("/api/admin/users/uuid")
	public Message findUuidByEmail(@RequestParam("email") String email) {
		String uuid = service.findUuidByEmail(email);

		if(uuid == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID is null");
		}

		Message message = new Message("ok", uuid);

		return message;
	}

	@DeleteMapping("/api/admin/users")
	public List<Users> deleteAdmin(@RequestParam("uuid") String uuid) {

		List<Users> user = service.deleteUser(uuid);
		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User not exists");
		}

		return user;
	}

	@GetMapping("/api/admin/users/list")
	public List<Users> getAllUsers() {
		return service.findAllUsers();
	}

}
