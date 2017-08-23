package ru.host.model;

import javax.persistence.*;
import java.util.Currency;

@Entity
@Table(name="procedure")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="procedure_id")
    private long id;
    @Column(name="procedure_name", nullable = false)
    private String name;
    @Column(name="procedure_description")
    private String description;
    @Column(name="procedure_price", nullable = false)
    private double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
