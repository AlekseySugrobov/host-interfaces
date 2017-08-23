package ru.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.host.model.DetailUser;

import java.util.List;

public interface DetailUserRepository extends JpaRepository<DetailUser, String> {

    DetailUser findByUsernameAndPassword(String username, String password);

    List<DetailUser> findByUsernameContaining(String username);
}
