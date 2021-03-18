package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Role;
import hha.spring.data.dataapi.domain.Users;
import hha.spring.data.dataapi.repository.RoleRepository;
import hha.spring.data.dataapi.repository.UserRepository;
import hha.spring.data.dataapi.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    private RoleRepository roleRepo;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepo;
        this.roleRepo = roleRepo;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String adminSignIn(String email, String password) {

        LOGGER.info("New Admin attempting to sign in "+email);
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

    public Users adminSignUP(String email, String password, String name) {
        LOGGER.info("New user attempting to sign up");
        Users user = userRepository.findByEmail(email);

        if( user == null) {
            Role role = roleRepo.findByRoleName("ROLE_ADMIN");

            UUID uuid = UUID.randomUUID();

            user = userRepository.save(new Users(email, passwordEncoder.encode(password), name, uuid.toString(), role));

            return user;
        }
        return null;
    }

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

    public List<Users> deleteUser(String uuid) {

        Users user = userRepository.findByUuid(uuid);

        if(user == null) {
            return null;
        }

        userRepository.delete(user);

        return findAllUsers();
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Users findByEmail(String email) {return userRepository.findByEmail(email);}

    public String findUuidByEmail(String email) {return userRepository.findByEmail(email).getUuid();}

}
