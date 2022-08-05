package com.company.ordersystem.entity.company;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "first_name_vocative")
    private String firstNameVocative;
    @Column (name = "surname")
    private String surname;

    @Column (name = "phone")
    private String phone;

    @Column (name = "phone_2")
    private String phone2;

    @Column (name = "email")
    private String email;

    @Column(name = "address_id", insertable = false, updatable = false)
    private int addressId;

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNameVocative() {
        return firstNameVocative;
    }

    public void setFirstNameVocative(String firstNameVocative) {
        this.firstNameVocative = firstNameVocative;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return firstName + " " + surname + "\nemail: " + email
                + "\ntel. " + phone + ", " + phone2 + " address ID" + addressId;
    }
}
