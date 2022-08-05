package com.company.ordersystem.entity.order;

import com.company.ordersystem.entity.company.Address;
import com.company.ordersystem.entity.company.Company;
import com.company.ordersystem.entity.product.Product;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "file_deadline")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fileDeadline;

    @Column(name = "delivery_deadline")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate deliveryDeadline;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "payment_term_id")
    private PaymentTerm paymentTerm;

    @ManyToMany ()
    @JoinTable(name = "campaign_products",
            joinColumns =@JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> productList;

    @ManyToMany ()
    @JoinTable(name = "campaign_orders",
            joinColumns =@JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    List<Order> orderList;

    public Campaign() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getFileDeadline() {
        return fileDeadline;
    }

    public void setFileDeadline(LocalDate fileDeadline) {
        this.fileDeadline = fileDeadline;
    }

    public LocalDate getDeliveryDeadline() {
        return deliveryDeadline;
    }

    public void setDeliveryDeadline(LocalDate deliveryDeadline) {
        this.deliveryDeadline = deliveryDeadline;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public PaymentTerm getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(PaymentTerm paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orders) {
        this.orderList = orders;
    }

    public void addProduct(Product product){
        if (productList == null){
            productList = new ArrayList<>();
        }
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public void addOrder (Order order){
        if (orderList ==null){
            orderList = new ArrayList<>();
        }
        orderList.add(order);
    }

    public void removeOrder(Order order){
        orderList.remove(order);
    }

    public Order getOrderByProduct (Product product){
        for (Order order: orderList){
            if (order.getProduct().equals(product)){
                return order;
            }
        }
        return null;
    }

    public boolean isProductInOrder (Product product){
        for (Order order: orderList){
            if (order.getProduct().equals(product)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", fileDeadline=" + fileDeadline +
                ", deliveryDeadline=" + deliveryDeadline +
                ", address=" + address +
                ", company=" + company +
                ", paymentTerm=" + paymentTerm +
                '}';
    }
}
