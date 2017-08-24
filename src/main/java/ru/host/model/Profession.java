package ru.host.model;

import javax.persistence.*;

@Entity
@Table(name="profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id")
    private long id;
    @Column(name="profession_name", nullable = false)
    private String name;
    @Column(name="profession_description")
    private String description;

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
}

