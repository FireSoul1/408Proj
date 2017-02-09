package com.greglturnquist.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

// tag::code[]
@Data
@Entity
public class User {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String description;

	private User() {}

	public User(String firstName, String lastName, String description) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
	}
}
// end::code[]
