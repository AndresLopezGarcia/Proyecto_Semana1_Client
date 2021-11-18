package com.bootCamp.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootCamp.client.entity.Client;

public interface ClientRepository extends ReactiveMongoRepository<Client, String>{

}
