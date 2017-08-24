package ru.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.host.model.DoctorOffice;

import java.util.List;

public interface DoctorOfficeRepository extends JpaRepository<DoctorOffice, Long> {
    List<DoctorOffice> findAllByOrderByNumberAsc();
}
