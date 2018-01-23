package com.packtpub.reactive;

import reactor.core.publisher.Flux;

public interface UserRepository {

	Flux<User> getAllUsers();
}