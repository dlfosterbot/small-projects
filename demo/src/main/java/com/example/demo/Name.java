package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class Name {
	
	@Column(name = "first_name") 
    private String firstName;

	@Column(name = "middle_name")
    private String middleName;

	@Column(name = "last_name")
    private String lastName;

    public Name() {

    }

    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    
}