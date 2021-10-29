package com.bootCamp1.app.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootCamp1.app.client.entity.Client;
import com.bootCamp1.app.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {

		return clientRepository.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public Client findById(String id) {

		return clientRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	/*
	 * public void save(Client client) {
	 * 
	 * clientRepository.save(client); }
	 */
	
	public Client save(Client client) {

		return clientRepository.save(client);
	}

	@Override
	@Transactional
	/*
	 * public void delete(Client client) {
	 * 
	 * clientRepository.delete(client); }
	 */
	public void delete(String id) {

		clientRepository.deleteById(id);
	}

}
