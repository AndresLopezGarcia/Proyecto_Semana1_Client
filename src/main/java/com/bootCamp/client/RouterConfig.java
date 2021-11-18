package com.bootCamp.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bootCamp.client.controller.ClientController;

@Configuration
public class RouterConfig {
	
	@Bean
	public RouterFunction<ServerResponse> routes(ClientController controller){
		
		return route(GET("/api/clients"), controller::list)
				.andRoute(GET("/api/clients/{id}"), controller::view)
				.andRoute(POST("/api/clients"), controller::create)
				.andRoute(PUT("/api/clients/{id}"), controller::edit)
				.andRoute(DELETE("/api/clients/{id}"), controller::delete);
	}

}
