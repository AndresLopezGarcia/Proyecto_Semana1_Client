package com.bootCamp1.app.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootCamp1.app.client.entity.Client;
import com.bootCamp1.app.client.service.ClientService;

@RestController
@RequestMapping("/apiClients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/list")
	public List<Client> findAll(){
		return clientService.findAll();
	}
	
	@GetMapping("/list/{id}")
	public Client findById(@PathVariable Long id){
		return clientService.findById(id);
	}
	
	@PostMapping("/client")
	public Client create(@RequestBody Client client) {
		clientService.save(client);
		return client;
		//return client.save(client);
	}
	
	@PutMapping("/client/{id}")
	public Client update(@RequestBody Client client,@PathVariable Long id) {
		Client currClient = this.clientService.findById(id);
		currClient.setName(client.getName());
		currClient.setLastName(client.getLastName());
		currClient.setDocumentType(client.getLastName());
		currClient.setDocumentNumber(client.getDocumentNumber());
		currClient.setAddress(client.getAddress());
		currClient.setEmail(client.getEmail());
		currClient.setPhone(client.getPhone());
		currClient.setDateofBirth(client.getDateofBirth());
		currClient.setClientType(client.getClientType());
		this.clientService.save(currClient);
		return currClient;
	}
	//public Client update(@RequestBody Client client,@PathVariable Long id) {
	//return client.save(client);
	//}

	//@DeleteMapping("/client/{id}")
	//public void delete(@PathVariable Long id) {
	//	Client currClient = this.clientService.delete(id);
	//}
	
}
