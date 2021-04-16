package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Role;
import hha.spring.data.dataapi.domain.Users;
import hha.spring.data.dataapi.repository.RoleRepository;
import hha.spring.data.dataapi.repository.UserRepository;
import hha.spring.data.dataapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * This class is a business logic to manage user data
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Service
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepo;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    /**
     * Instantiates a new User service.
     *
     * @param userRepo              the user repo
     * @param roleRepo              the role repo
     * @param authenticationManager the authentication manager
     * @param passwordEncoder       the password encoder
     * @param jwtUtil               the jwt util
     */
    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepo;
        this.roleRepo = roleRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Admin sign in string.
     *
     * @param email    the email
     * @param password the password
     * @return the string
     */
    public String adminSignIn(String email, String password) {

        String token = "";
        Users user = userRepository.findByEmail(email);

        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        else {

            if(user.getActive() == 0) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not activated");
            }

            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
                token = jwtUtil.createToken(email, user.getRoles());

            } catch (AuthenticationException e) {
                //throw when password is not match
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            } catch (Exception ee) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ee.getMessage());
            }
        }

        return token;
    }

    /**
     * Admin sign up users.
     *
     * @param email    the email
     * @param password the password
     * @param name     the name
     * @return the users
     */
    public Users adminSignUP(String email, String password, String name) {
        Users user = userRepository.findByEmail(email);

        if( user == null) {
            Role role = roleRepo.findByRoleName("ROLE_ADMIN");

            UUID uuid = UUID.randomUUID();

            user = userRepository.save(new Users(email, passwordEncoder.encode(password), name, uuid.toString(), role));

            return user;
        }
        return null;
    }

    /**
     * Admin activate users.
     *
     * @param uuid the uuid
     * @return the users
     */
    public Users adminActivate(String uuid) {
        Users user = userRepository.findByUuid(uuid);

        if(user == null) {
            return null;
        }

        UUID newUuid = UUID.randomUUID();
        user.setUuid(newUuid.toString());
        user.setActive(1);

        userRepository.save(user);

        return user;
    }

    /**
     * Admin de activate users.
     *
     * @param uuid the uuid
     * @return the users
     */
    public Users adminDeActivate(String uuid) {

        Users user = userRepository.findByUuid(uuid);

        if(user == null) {
            return null;
        }

        UUID newUuid = UUID.randomUUID();
        user.setUuid(newUuid.toString());
        user.setActive(0);

        userRepository.save(user);

        return user;
    }

    /**
     * Delete user list.
     *
     * @param uuid the uuid
     * @return the list
     */
    public List<Users> deleteUser(String uuid) {

        Users user = userRepository.findByUuid(uuid);

        if(user == null) {
            return null;
        }

        userRepository.delete(user);

        return findAllUsers();
    }

    /**
     * Find all users list.
     *
     * @return the list
     */
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find by email users.
     *
     * @param email the email
     * @return the users
     */
    public Users findByEmail(String email) {return userRepository.findByEmail(email);}

    /**
     * Find uuid by email string.
     *
     * @param email the email
     * @return the string
     */
    public String findUuidByEmail(String email) {return userRepository.findByEmail(email).getUuid();}

}
