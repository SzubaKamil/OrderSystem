package com.company.ordersystem.entity.order;

import javax.persistence.*;

@Entity
@Table(name = "payment_term")
public class PaymentTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "term")
    private String term;

    public PaymentTerm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "PaymentTerm{" +
                "id=" + id +
                ", term='" + term + '\'' +
                '}';
    }
}
