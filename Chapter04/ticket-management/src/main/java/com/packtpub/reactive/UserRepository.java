package com.packtpub.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

	Flux<User> getAllUsers();
	
	Mono<User> getUser(Integer id);
	
	Mono<Void> saveUser(Mono<User> userMono);
	
	Mono<Void> updateUser(Mono<User> userMono);
	
	Mono<Void> deleteUser(Integer id);
}