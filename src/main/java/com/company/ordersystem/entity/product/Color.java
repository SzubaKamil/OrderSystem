package com.company.ordersystem.entity.product;

import javax.persistence.*;

@Entity
@Table(name = "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name = "value")
    private String value;

    public Color() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String format) {
        this.value = format;
    }

    @Override
    public String toString() {
        return "Color{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
