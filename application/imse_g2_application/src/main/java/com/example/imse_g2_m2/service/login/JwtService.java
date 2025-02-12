package com.example.imse_g2_m2.service.login;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


/**
 * Class that handles JWT, tokens, etc
 */
@Service
//@AllArgsConstructor
public class JwtService {

	
	private String myKey = "";
	
    public JwtService() {
//        this.authManager = authManager;

        // Initialize the key
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            this.myKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		
		return Jwts.builder()
					.claims()
					.add(claims)
					.subject(username)
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 45)) // seconds - minutes - num of minutes
					.and()
					.signWith(getKey())
					.compact();
	}
	
	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(myKey);
		return Keys.hmacShaKeyFor(keyBytes); // Okay for demo
	}

	public String extractUsername(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

	
}







