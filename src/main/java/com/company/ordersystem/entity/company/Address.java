package com.company.ordersystem.entity.company;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name = "street")
    private String street;

    @Column (name = "street_number")
    private String streetNumber;

    @Column (name = "city")
    private String city;

    @Column (name = "post_code")
    private String postCode;

    @Column (name = "country")
    private String country;

    @Column (name = "is_delivery")
    private boolean isDelivery;

//    @OneToMany()
//    @OneToMany(cascade = CascadeType.ALL)
    @OneToMany( cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}) // correct for save
    @JoinColumn(name = "address_id")
    private List<Contact> contacts;

    public Address() {
    }

    public boolean addContact (Contact contact){
        if (contacts == null){
            contacts = new ArrayList<>();
        }
        return contacts.add(contact);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return street + " " + streetNumber +
                "\n" + postCode + " " + city + " " + country;
    }
}
