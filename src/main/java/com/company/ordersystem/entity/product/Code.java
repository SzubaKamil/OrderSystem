package com.company.ordersystem.entity.product;


import javax.persistence.*;
import java.text.DecimalFormat;


@Entity
@Table(name = "code")
public class Code implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_id",  insertable=false, updatable=false)
    private Integer productId;

    public Code() {
    }

    public Code (Code code){
        this.code = code.getCode();
        this.quantity = code.getQuantity();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(code);
        if (quantity > 0){
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
            sb.append(" - ")
                    .append(decimalFormat.format(quantity))
                    .append(" egz.");
        }
        return sb.toString();
    }

    @Override
    protected Code clone() throws CloneNotSupportedException {
        return new Code(this);
    }
}
