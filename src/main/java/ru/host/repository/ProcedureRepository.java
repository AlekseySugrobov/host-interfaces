package ru.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.host.model.Procedure;

import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    List<Procedure> findAllByOrderByNameAsc();
}
