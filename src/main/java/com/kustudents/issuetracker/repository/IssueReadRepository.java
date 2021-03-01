package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.IssueRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface IssueReadRepository extends JpaRepository<IssueRead, Long> {

    @Query("SELECT IR FROM ciw_Issues IR where " +
            "(( IR.closed IS NULL AND  :showClosed = true) OR (:showClosed = false))" +
            "AND (IR.createdBy = :userCreated OR :userCreated IS NULL)" +
            "AND (IR.currentResponsible = :userResponsible OR :userResponsible IS NULL)" )
    Page<IssueRead> getAllIssuesPaginatedAndFiltered(Boolean showClosed, String userCreated, String userResponsible, Pageable p);

}
