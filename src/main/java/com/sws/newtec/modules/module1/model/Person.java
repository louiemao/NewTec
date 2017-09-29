package com.sws.newtec.modules.module1.model;

import org.springframework.data.annotation.Id;

/**
 * Created by george on 15-7-1.
 */
public class Person {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
