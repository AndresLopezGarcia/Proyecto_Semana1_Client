package com.bootCamp.client.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "clients")
public class Client {
	
	@Id
	private String id;
	
	@Field("name")
	private String name;
	
	@Field("lastName")
	private String lastName;
	
	@Field("documentType")
	private String documentType;
	
	@Field("documentNumber")
	private String documentNumber;
	
	@Field("address")
	private String address;
	
	@Field("email")
	private String email;
	
	@Field("phone")
	private String phone;
	
	@Field("dateofBirth")
	private LocalDate dateofBirth;
	
	@Field("ClientType")
	private String clientType;

}
