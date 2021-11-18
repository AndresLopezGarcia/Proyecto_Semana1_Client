package com.bootCamp.client.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bootCamp.client.entity.Client;
import com.bootCamp.client.service.ClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@Autowired
	private Validator validator;
	
	public Mono<ServerResponse> list(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAll(), Client.class);
	}
	
	public Mono<ServerResponse> view(ServerRequest request){
		String id = request.pathVariable("id");
		return service.findById(id).flatMap(c -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(c))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> create(ServerRequest request){
		Mono<Client> client = request.bodyToMono(Client.class);
		
		return client.flatMap(c -> {
			Errors errors = new BeanPropertyBindingResult(c, Client.class.getName());
			validator.validate(c, errors);
			
			if (errors.hasErrors()) {
				return Flux.fromIterable(errors.getFieldErrors())
						.map(fieldError -> "The field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
						.collectList()
						.flatMap(list -> ServerResponse.badRequest().bodyValue(list));
			}
			return service.save(c).flatMap(pdb -> ServerResponse
					.created(URI.create("/api/clients/".concat(pdb.getId())))
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(pdb));
		});
	}
	
	public Mono<ServerResponse> edit(ServerRequest request){
		Mono<Client> client = request.bodyToMono(Client.class);
		String id = request.pathVariable("id");
		
		Mono<Client> clientDb = service.findById(id);
		
		return clientDb.zipWith(client, (db,req) -> {
			db.setName(req.getName());
			db.setLastName(req.getLastName());
			db.setDocumentType(req.getDocumentType());
			db.setDocumentNumber(req.getDocumentNumber());
			db.setAddress(req.getAddress());
			db.setEmail(req.getEmail());
			db.setPhone(req.getPhone());
			db.setDateofBirth(req.getDateofBirth());
			db.setClientType(req.getClientType());
			return db;
		}).flatMap(c -> ServerResponse.created(URI.create("/api/clients/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.save(c), Client.class))
			.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> delete(ServerRequest request){
		String id = request.pathVariable("id");
		
		Mono<Client> clientDb = service.findById(id);
		
		return clientDb.flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

}
