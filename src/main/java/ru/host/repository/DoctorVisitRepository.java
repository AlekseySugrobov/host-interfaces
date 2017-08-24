package ru.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.host.model.DoctorVisit;

import java.util.List;

public interface DoctorVisitRepository extends JpaRepository<DoctorVisit, Long> {
    List<DoctorVisit> findAllByOrderByDayOfWeekAscDoctor_LastNameAscDoctor_FirstNameAscProcedure_NameAsc();
}
