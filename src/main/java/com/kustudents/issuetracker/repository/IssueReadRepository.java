package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.IssueRead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueReadRepository extends JpaRepository<IssueRead, Long> {
    
}
