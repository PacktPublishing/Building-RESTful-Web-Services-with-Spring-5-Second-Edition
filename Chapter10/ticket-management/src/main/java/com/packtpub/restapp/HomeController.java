package com.packtpub.restapp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.packtpub.aop.TokenRequired;
import com.packtpub.service.SecurityService;

@RestController
@RequestMapping("/")
public class HomeController extends ErrorHandler {

	private static final Logger _logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	SecurityService securityService;

	@ResponseBody
	@RequestMapping("")
	public Map<String, Object> test() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", "Aloha");

		_logger.trace("{test} trace");
		_logger.debug("{test} debug");
		_logger.info("{test} info");
		_logger.warn("{test} warn ");
		_logger.error("{test} error");

		return map;
	}

	@ResponseBody
	@RequestMapping("/test/aop/with/annotation")
	@TokenRequired
	public Map<String, Object> testAOPAnnotation() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", "Aloha");

		return map;
	}

	@ResponseBody
	@RequestMapping("/test/aop/with/execution")
	public Map<String, Object> testAOPExecution() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", "Aloha");

		return map;
	}

	@ResponseBody
	@RequestMapping("/security/generate/token")
	public Map<String, Object> generateToken(@RequestParam(value = "subject") String subject) {

		String token = securityService.createToken(subject, (15 * 1000 * 60));

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", token);

		return map;
	}

	@ResponseBody
	@RequestMapping("/security/get/subject")
	public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {

		String subject = securityService.getSubject(token);

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", subject);

		return map;
	}

	// other methods
	@ResponseBody
	@RequestMapping("/test/error")
	public Map<String, Object> testError(@RequestParam(value = "item") String item) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("item", item);
		return map;
	}
	
	// other methods
	@ResponseBody
	@RequestMapping("/test/error/{id}")
	public Map<String, Object> testRuntimeError(@PathVariable("id") Integer id){
		if(id == 1){
			throw new RuntimeException("some exception");
		}
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", "one");
		return map;
	}
}