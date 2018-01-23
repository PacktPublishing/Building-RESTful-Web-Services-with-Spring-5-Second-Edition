package com.packtpub.aop;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.packtpub.service.SecurityServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Aspect
@Component
public class TokenRequiredAspect {
	
	@Before("execution(* com.packtpub.restapp.HomeController.testAOPExecution())")
	public void tokenRequiredWithoutAnnoation() throws Throwable{
		System.out.println("Before tokenRequiredWithExecution");
	}
	
	@Before("@annotation(tokenRequired)")
	public void tokenRequiredWithAnnotation(TokenRequired tokenRequired) throws Throwable{
		
		System.out.println("Before tokenRequiredWithAnnotation");
		
		ServletRequestAttributes reqAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = reqAttributes.getRequest();
		
		// checks for token in request header
		String tokenInHeader = request.getHeader("token");
		
		if(StringUtils.isEmpty(tokenInHeader)){
			throw new IllegalArgumentException("Empty token");
		}
		
		Claims claims = Jwts.parser()         
			       .setSigningKey(DatatypeConverter.parseBase64Binary(SecurityServiceImpl.secretKey))
			       .parseClaimsJws(tokenInHeader).getBody();
		
		if(claims == null || claims.getSubject() == null){
			throw new IllegalArgumentException("Token Error : Claim is null");
		}
		
		if(!claims.getSubject().equalsIgnoreCase("packt")){
			throw new IllegalArgumentException("Subject doesn't match in the token");
		}
	}
}
