package com.clothesstore.api.Models;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    private String name;
    private String description;
    private BigDecimal price;
    private double discount_rate;
    private long search_amount;
    private String urlImageaRear ;
    private String urlImageFront ;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(double discount_rate) {
        this.discount_rate = discount_rate;
    }

    public long getSearch_amount() {
        return search_amount;
    }

    public void setSearch_amount(long search_amount) {
        this.search_amount = search_amount;
    }


    public String getUrlImageaRear() {
        return urlImageaRear;
    }

    public void setUrlImageaRear(String urlImageaRear) {
        this.urlImageaRear = urlImageaRear;
    }

    public String getUrlImageFront() {
        return urlImageFront;
    }

    public void setUrlImageFront(String urlImageFront) {
        this.urlImageFront = urlImageFront;
    }

}
