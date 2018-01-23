package com.packtpub.reactive;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserHandler {

	private final UserRepository userRepository;

	public UserHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Mono<ServerResponse> getAllUsers(ServerRequest request) {
		Flux<User> users = this.userRepository.getAllUsers();		
		
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(users, User.class);
	}
}