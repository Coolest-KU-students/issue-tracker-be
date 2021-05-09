package com.kustudents.issuetracker.repository;

import java.util.Optional;

import com.kustudents.issuetracker.model.UserRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserReadRepository extends JpaRepository<UserRead, Long> {

    Optional<UserRead> findByLogin(String login);

    @Query("SELECT U FROM ciw_Users U WHERE is_expired = :showExpired")
    Page<UserRead> getUsersPaginatedAndFiltered(@Param("showExpired") Boolean showExpired, Pageable p);
}
