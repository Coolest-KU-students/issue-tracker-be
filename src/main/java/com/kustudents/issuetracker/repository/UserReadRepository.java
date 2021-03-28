package com.kustudents.issuetracker.repository;

import java.util.Optional;

import com.kustudents.issuetracker.model.entity.UserRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReadRepository extends JpaRepository<UserRead, Long> {

    Optional<UserRead> findByLogin(String login);
}
