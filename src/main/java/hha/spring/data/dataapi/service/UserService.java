package hha.spring.data.dataapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hha.spring.data.dataapi.domain.User;
import hha.spring.data.dataapi.repository.UserRepository;
import hha.spring.data.dataapi.security.UserPrincipal;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository repo;
	
	public void saveUser(User user) {
		repo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if( user == null ) {
			throw new UsernameNotFoundException("Cannot find username: " + username);
		}
		return new UserPrincipal(user);
	}
}
