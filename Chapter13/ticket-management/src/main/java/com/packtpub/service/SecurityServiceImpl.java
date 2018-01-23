package com.packtpub.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	public static final String secretKey= "4C8kum4LxyKWYLM78sKdXrzbBjDCFyfX";
	
	@Override
	public String createToken(String subject, long ttlMillis) {
		
		if (ttlMillis <= 0) {
			throw new RuntimeException("Expiry time must be greater than Zero :["+ttlMillis+"] ");
		}
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// The JWT signature algorithm we will be using to sign the token
		long nowMillis = System.currentTimeMillis();

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder()
				.setSubject(subject)				
				.signWith(signatureAlgorithm, signingKey);
		
		builder.setExpiration(new Date(nowMillis + ttlMillis));		

		return builder.compact();
	}
	
	@Override
	public String getSubject(String token) {		
		
		Claims claims = Jwts.parser()         
			       .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
			       .parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
}
