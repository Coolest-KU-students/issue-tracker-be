package com.kustudents.issuetracker.repository;

import java.util.Optional;

import com.kustudents.issuetracker.model.entity.IssueRead;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.Query;

public interface IssueReadRepo extends CrudRepository<IssueRead, Long> {
    
    //TODO: move to users once it's created
    @Query( nativeQuery=true, value="SELECT fnc_user_login()")
    public Optional<String> GetUserName();
    
    @Query("SELECT IR FROM ciw_Issues IR where " +
            "(( IR.closed IS NULL AND  :showClosed = true) OR (:showClosed = false))" +
            "AND (IR.createdBy = :userCreated OR :userCreated IS NULL)" +
            "AND (IR.currentResponsible = :userResponsible OR :userResponsible IS NULL)" )
    Page<IssueRead> getAllIssuesPaginatedAndFiltered(Boolean showClosed, String userCreated, String userResponsible, Pageable p);
    


}
