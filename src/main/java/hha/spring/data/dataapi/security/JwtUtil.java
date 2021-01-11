package hha.spring.data.dataapi.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	private String secret = "hhastore";
	
	public String generateToken (String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 1 day
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public String getUsername (String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public Boolean isTokenExpired (String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				.before(new Date());
	}
	
	public Boolean validateToken (String token, UserDetails userDetails) {
		return getUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
}
