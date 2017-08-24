package ru.host.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@Table(name="doctor_visit")
public class DoctorVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_visit_id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="doctor_visit_doctor_id", nullable = false)
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="doctor_visit_procedure_id", nullable = false)
    private Procedure procedure;
    @Column(name="doctor_visit_day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;
    @Column(name="doctor_visit_time", nullable = false)
    private Time time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
