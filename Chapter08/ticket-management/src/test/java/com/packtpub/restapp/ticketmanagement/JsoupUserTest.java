package com.packtpub.restapp.ticketmanagement;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.packtpub.model.User;
import com.packtpub.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsoupUserTest {

	private final Logger _log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void testUsersJsoup() throws IOException {

		String doc = Jsoup.connect("http://localhost:8080/user").ignoreContentType(true).get().body().text();

		_log.info("{test} doc : " + doc);

		JsonParser parser = new JsonParser();
		JsonElement userElement = parser.parse(doc);
		JsonArray userArray = userElement.getAsJsonArray();

		_log.info("{test} size : " + userArray.size());

		assertEquals(4, userArray.size());
	}

	@Test
	public void testUserJsoup() throws IOException {

		String doc = Jsoup.connect("http://localhost:8080/user/100").ignoreContentType(true).get().body().text();

		Gson g = new Gson();
		User user = g.fromJson(doc, User.class);

		assertEquals("David", user.getUsername());		
		
	}
	
	@Autowired
	UserService userSevice;

	@Test
	public void testUserAdditionJsoup() throws IOException {

		String doc = Jsoup.connect("http://localhost:8080/user/").data("userid", "103").data("username", "kevin")
				.ignoreContentType(true).post().body().text();

		Gson g = new Gson();
		Map<String, Object> result = g.fromJson(doc, Map.class);

		_log.info("{test} result : " + result);

		assertEquals("added", result.get("result"));
		
		// user should be deleted as we tested the case already  
		userSevice.deleteUser(103);
	}
}
