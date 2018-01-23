package com.packtpub.reactive;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.ipc.netty.http.server.HttpServer;

public class Server {

	public static final String HOST = "localhost";
	public static final int PORT = 8081;

	public static void main(String[] args) throws InterruptedException, IOException {
		Server server = new Server();
		server.startReactorServer();

		System.out.println("Press ENTER to exit.");
		System.in.read();
	}

	public void startReactorServer() throws InterruptedException {
		RouterFunction<ServerResponse> route = routingFunction();
		HttpHandler httpHandler = toHttpHandler(route);

		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
		HttpServer server = HttpServer.create(HOST, PORT);
		server.newHandler(adapter).block();
	}

	public RouterFunction<ServerResponse> routingFunction() {
		UserRepository repository = new UserRepositorySample();
		UserHandler handler = new UserHandler(repository);
		
		return nest (
			path("/user"),
			nest(
				accept(MediaType.ALL),
				route(GET("/"), handler::getAllUsers)
			)
			.andRoute(GET("/{id}"), handler::getUser)
			.andRoute(POST("/").and(contentType(APPLICATION_JSON)), handler::createUser)
			.andRoute(PUT("/").and(contentType(APPLICATION_JSON)), handler::updateUser)
			.andRoute(DELETE("/{id}").and(contentType(APPLICATION_JSON)), handler::deleteUser)
		);
	}
}