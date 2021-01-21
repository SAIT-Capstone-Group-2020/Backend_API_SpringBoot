package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Role;
import hha.spring.data.dataapi.domain.Users;
import hha.spring.data.dataapi.repository.RoleRepository;
import hha.spring.data.dataapi.repository.UserRepository;
import hha.spring.data.dataapi.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String customerSignIn(String username, String password) {

        LOGGER.info("New Customer attempting to sign in "+username);
        String token = "";
        Users user = userRepository.findByUsername(username);

        if(user != null) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = jwtUtil.createToken(username, user.getRoles());
            } catch (AuthenticationException e) {
                LOGGER.info("Log in failed for user {}", username);
            }
        }
        return token;
    }

    public Users customerSignUP(String username, String password) {
        LOGGER.info("New user attempting to sign up");
        Users user = userRepository.findByUsername(username);
        //Customer
        if( user == null) {
            Role role = roleRepo.findByRoleName("ROLE_CUSTOMER");

            LOGGER.info("New user attempting to sign up");

            user = userRepository.save(new Users(username, passwordEncoder.encode(password), role));
            //Customer.save
        }
        return user;
    }

}
