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

/**
 * This class is a Spring controller which serializes
 * every 'user' related request handling methods.
 * This controller uses UserService.
 * This allows the cross origin request from all host
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService service;

    /**
     * Sign-in with id(email) & password
     *
     * @param loginDto - data entity to retrieve the user data
     * @return Message(success message with JWT token / failed) message
     */
    @PostMapping("/api/admin/signin")
	public Message login(@RequestBody LoginDto loginDto) {

		String token = service.adminSignIn(loginDto.getEmail(), loginDto.getPassword());

		Message message = new Message("ok", token);

		return message;
	}

    /**
     * Sign-up (create new account)
     *
     * @param loginDto - data entity to retrieve the user data
     * @return Users object with the registered information
     */
    @PutMapping("/api/admin/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public Users signup(@RequestBody LoginDto loginDto) {

		Users user = service.adminSignUP(loginDto.getEmail(), loginDto.getPassword(), loginDto.getUser_name());

		if(user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
		}

		return user;
	}

    /**
     * activate user's account. By default, all user account is unactivated when they created it first.
     *
     * @param uuid - uuid which can be retrieved with findUuidByEmail method
     * @return Updated user object data
     */
    @PutMapping("/api/admin/users/activate")
	public Users activateAdmin(@RequestParam("uuid") String uuid) {

		Users user = service.adminActivate(uuid);

		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User not exists");
		}

		return user;
	}

    /**
     * deactivate user's account.
     *
     * @param uuid - uuid which can be retrieved with findUuidByEmail method
     * @return Updated user object data
     */
    @PutMapping("/api/admin/users/deactivate")
	public Users deactivateAdmin(@RequestParam("uuid") String uuid) {

		Users user = service.adminDeActivate(uuid);

		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User not exists");
		}

		return user;
	}

    /**
     * get uuid information for the user
     *
     * @param email - email(id) of the user
     * @return Message with UUID or failed message
     */
    @GetMapping("/api/admin/users/uuid")
	public Message findUuidByEmail(@RequestParam("email") String email) {
		String uuid = service.findUuidByEmail(email);

		if(uuid == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID is null");
		}

		Message message = new Message("ok", uuid);

		return message;
	}

    /**
     * delete user
     *
     * @param uuid - uuid which can be retrieved with findUuidByEmail method
     * @return Update list of all user
     */
    @DeleteMapping("/api/admin/users")
	public List<Users> deleteAdmin(@RequestParam("uuid") String uuid) {

		List<Users> user = service.deleteUser(uuid);
		if(user == null) {
			new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User not exists");
		}

		return user;
	}

    /**
     * get list of all user
     *
     * @return list of all user
     */
    @GetMapping("/api/admin/users/list")
	public List<Users> getAllUsers() {
		return service.findAllUsers();
	}

}
