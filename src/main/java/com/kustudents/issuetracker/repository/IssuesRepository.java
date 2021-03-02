package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesRepository extends JpaRepository<Issue, Long> {
    
}
