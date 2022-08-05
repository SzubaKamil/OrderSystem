package com.company.ordersystem.entity.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", unique=true, columnDefinition="VARCHAR(50)")
    private String username;

    @Column(name = "password")
    private String password;

    @Column (name = "enabled")
    private int enabled;

    @Column (name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone_2")
    private String phone2;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Authority authority;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private OutlookSetting outlookSetting;

    public User() {
        this.outlookSetting = new OutlookSetting();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = "{noop}" + password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public OutlookSetting getOutlookSetting() {
        return outlookSetting;
    }

    public void setOutlookSetting(OutlookSetting outlookSetting) {
        this.outlookSetting = outlookSetting;
    }

    @Override
    public String toString() {
        return "Kontakt: " + name + " " + surname + " tel. " + phone +  ", " + phone2 +
        " email. " + email;
    }
}
