package ru.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.host.model.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByOrderByLastNameAsc();
}
