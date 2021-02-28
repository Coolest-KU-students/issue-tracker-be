package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    @Query( nativeQuery=true, value="SELECT fnc_user_login()")
    Optional<String> getLogin();

}
