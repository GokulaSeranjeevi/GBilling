package com.skywings.gbilling.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class CreateJsonFile {
	public static void main(String[] args) {
		// Create a sample Java object
		Person person = new Person("John", "Doe", 30);

		// Create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Write the object to a JSON file
			objectMapper.writeValue(new File("person.json"), person);
			System.out.println("JSON file created successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// Define a Person class
class Person {
	private String firstName;
	private String lastName;
	private int age;

	// Constructor
	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	// Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
