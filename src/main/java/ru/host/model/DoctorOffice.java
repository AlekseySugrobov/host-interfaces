package ru.host.model;

import javax.persistence.*;

@Entity
@Table(name="doctor_office")
public class DoctorOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_office_id")
    private long id;
    @Column(name="doctor_office_number", nullable = false)
    private String number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorOffice that = (DoctorOffice) o;

        if (id != that.id) return false;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + number.hashCode();
        return result;
    }
}
