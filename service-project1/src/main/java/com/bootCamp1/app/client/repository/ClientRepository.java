package com.bootCamp1.app.client.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bootCamp1.app.client.entity.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, Long>{

}
