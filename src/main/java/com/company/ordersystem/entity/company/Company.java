package com.company.ordersystem.entity.company;


import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "shortcut")
    private String shortcut;

    @Column (name = "name")
    private String name;

    @Column(name = "nip")
    private String nip;

    @Column(name = "regon")
    private String regon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);

        if (!nip.equals("")){
            sb.append("\nNIP: ")
                    .append(nip);
        }
        if(!regon.equals("")){
            sb.append(" REGON: ")
                    .append(regon);
        }
        sb.append("\n").append(address);

        return sb.toString();
    }
}
