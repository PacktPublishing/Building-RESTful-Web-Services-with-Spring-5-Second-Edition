package com.packtpub.reactive;

import java.util.Map;

import reactor.core.publisher.Flux;

public class UserRepositorySample implements UserRepository {

	// initiate Users
	private Map<Integer, User> users = null;

	// fill dummy values for testing
	public UserRepositorySample() {
		
		
		// Java 9 Immutable map used
		users = Map.of(
				1, (new User(1, "David")),
				2, (new User(2, "John")),
				3, (new User(3, "Kevin"))
		);		
	}

	// this method will return all users
	@Override
	public Flux<User> getAllUsers() {
		return Flux.fromIterable(this.users.values());
	}
}