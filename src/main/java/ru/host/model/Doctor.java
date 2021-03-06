package ru.host.model;

import javax.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private long id;
    @Column(name="doctor_first_name", nullable = false)
    private String firstName;
    @Column(name="doctor_middle_name", nullable = false)
    private String middleName;
    @Column(name="doctor_last_name", nullable = false)
    private String lastName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="doctor_profession_id")
    private Profession profession;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="doctor_office_id")
    private DoctorOffice doctorOffice;

    public DoctorOffice getDoctorOffice() {
        return doctorOffice;
    }

    public void setDoctorOffice(DoctorOffice doctorOffice) {
        this.doctorOffice = doctorOffice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getFullName(){
        return lastName + " " + firstName.charAt(0) + ". " + middleName.charAt(0) + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (id != doctor.id) return false;
        if (!firstName.equals(doctor.firstName)) return false;
        if (!middleName.equals(doctor.middleName)) return false;
        return lastName.equals(doctor.lastName);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
