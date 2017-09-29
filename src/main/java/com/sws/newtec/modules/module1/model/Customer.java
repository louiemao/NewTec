package com.sws.newtec.modules.module1.model;

import org.springframework.data.annotation.Id;

/**
 * Created by george on 15-6-29.
 */
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    public Customer(){

    }
    public Customer(String firstName,String lastName){
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                getId(), getFirstName(), getLastName());
    }

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
}
