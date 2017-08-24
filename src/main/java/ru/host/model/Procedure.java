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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Procedure procedure = (Procedure) o;

        if (id != procedure.id) return false;
        if (Double.compare(procedure.price, price) != 0) return false;
        if (!name.equals(procedure.name)) return false;
        return description != null ? description.equals(procedure.description) : procedure.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
