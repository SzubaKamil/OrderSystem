package com.company.ordersystem.entity.order;


import com.company.ordersystem.entity.company.Address;
import com.company.ordersystem.entity.company.Company;
import com.company.ordersystem.entity.company.Contractor;
import com.company.ordersystem.entity.product.Product;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "file_deadline")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fileDeadline;

    @Column(name = "delivery_deadline")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate deliveryDeadline;

    @Column(name = "sent")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate sent;

    @Column(name = "price_pcs")
    private double pricePcs;
    @ManyToOne
    @JoinColumn(name = "payment_term_id")
    private PaymentTerm paymentTerm;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "username")
    private String username;

    @Column(name = "[update]")
    private Integer update;


    @Column (name = "note")
    private String note;

    @OneToOne (cascade = {CascadeType.MERGE})
    @JoinTable (name = "campaign_orders",
            joinColumns =@JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "campaign_id"))
    Campaign campaign;


    public Order() {
    }

    public Order(Campaign campaign, Product product){
        this.product = product;
        this.quantity = campaign.getQuantity();
        this.fileDeadline = campaign.getFileDeadline();
        this.deliveryDeadline = campaign.getDeliveryDeadline();
        this.deliveryAddress = campaign.getAddress();
        this.company = campaign.getCompany();
        this.paymentTerm = campaign.getPaymentTerm();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getSent() {
        return sent;
    }

    public boolean setSent(LocalDate sent) {
        if (sent != null){
            if (this.sent == null){
                this.sent = sent;
                update = 0;
                return true;
            }
            else {
                update ++;
            }
        }
        return false;
    }

    public void setDeliveryDeadline(LocalDate deliveryDeadline) {
        this.deliveryDeadline = deliveryDeadline;
    }

    public double getPricePcs() {
        return pricePcs;
    }

    public void setPricePcs(double pricePcs) {
        this.pricePcs = pricePcs;
    }

    public PaymentTerm getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(PaymentTerm paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUpdate() {
        return update;
    }

    public void setUpdate(Integer update) {
        this.update = update;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", fileDeadline=" + fileDeadline +
                ", deliveryDeadline=" + deliveryDeadline +
                ", pricePcs=" + pricePcs +
                ", sent=" + sent +
                ", paymentTerm=" + paymentTerm +
                ", deliveryAddress=" + deliveryAddress +
                ", product=" + product +
                ", contractor=" + contractor +
                ", company=" + company +
                ", username='" + username + '\'' +
                '}';
    }
}
