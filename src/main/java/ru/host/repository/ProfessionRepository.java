package ru.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.host.model.Profession;

import java.util.List;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    List<Profession> findAllByOrderByNameAsc();
}
