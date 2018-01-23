package com.packtpub.reactive;

import java.util.HashMap;
import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserRepositorySample implements UserRepository {

	// initiate Users
	private final Map<Integer, User> users = new HashMap<>();

	// fill dummy values for testing
	public UserRepositorySample() {
		this.users.put(100, new User(100, "David"));
		this.users.put(101, new User(101, "John"));
		this.users.put(102, new User(102, "Kevin"));
	}

	// this method will return all users
	@Override
	public Flux<User> getAllUsers() {
		return Flux.fromIterable(this.users.values());
	}
	
	@Override
	public Mono<User> getUser(Integer id){
		return Mono.justOrEmpty(this.users.get(id));		
	}
	
	@Override
	public Mono<Void> saveUser(Mono<User> userMono) {
		return userMono.doOnNext(user -> {			
			users.put(user.getUserid(), user);
			System.out.format("Saved %s with id %d%n", user, user.getUserid());
		}).thenEmpty(Mono.empty());
	}
	
	@Override
	public Mono<Void> updateUser(Mono<User> userMono) {
		return userMono.doOnNext(user -> {			
			users.put(user.getUserid(), user);
			System.out.format("Saved %s with id %d%n", user, user.getUserid());
		}).thenEmpty(Mono.empty());
	}
	
	@Override
	public Mono<Void> deleteUser(Integer id) {
		
		users.remove(id);		
		System.out.println("user : "+users);
		
		return Mono.empty();
	}
}