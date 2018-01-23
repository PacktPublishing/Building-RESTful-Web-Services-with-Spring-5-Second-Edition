package com.packtpub.restapp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.packtpub.model.User;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	private final Logger _log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate template;
	
	@ResponseBody
	@RequestMapping("/test")	
	public Map<String, Object> test(){
		Map<String, Object> map = new LinkedHashMap<>();
		
		String content = template.getForObject("http://localhost:8080/", String.class);		
		map.put("result", content);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/test/user")	
	public Map<String, Object> testGetUser(){
		Map<String, Object> map = new LinkedHashMap<>();
		
		User user = template.getForObject("http://localhost:8080/user/100", User.class);		
		map.put("result", user);
		
		return map;
	}
}