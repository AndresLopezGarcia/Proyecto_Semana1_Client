package com.bootCamp.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootCamp.client.entity.Client;
import com.bootCamp.client.repository.ClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository repository;

	@Override
	public Flux<Client> findAll() {
		
		return repository.findAll();
	}

	@Override
	public Mono<Client> findById(String id) {
		
		return repository.findById(id);
	}

	@Override
	public Mono<Client> save(Client client) {
		
		return repository.save(client);
	}

	@Override
	public Mono<Void> delete(Client client) {
		
		return repository.delete(client);
	}

}
