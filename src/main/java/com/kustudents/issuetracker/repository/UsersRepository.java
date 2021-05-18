package com.kustudents.issuetracker.repository;

import java.util.Optional;

import com.kustudents.issuetracker.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

}
