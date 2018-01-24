package com.revenant.takego_secondapp.model.entities;

/**
 * Created by Or on 22-Jan-18.
 */

public class Customer {
    private String name;
    private String lastName;
    private long id;
    private String phoneNumber;
    private String email;
    private String creditCard;
    private String password;

    public Customer(String name, String lastName, long id, String phoneNumber, String email, String creditCard) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creditCard = creditCard;
    }

    public Customer() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        String customer = "ID: " + id +"\n"
                + "Password: " + password +"\n"
                + "Name: " + name + "\n"
                + "Last name: " + lastName + "\n"
                + "Phone number: "+ phoneNumber + "\n"
                + "Email address: " + email + "\n"
                + "Credit card: " + creditCard;
        return customer;
    }
}
