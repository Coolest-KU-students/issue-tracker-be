package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.IssueRead;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueReadRepo extends JpaRepository<IssueRead, Long> {
    
}
