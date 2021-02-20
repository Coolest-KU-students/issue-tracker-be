package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.Issue;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuesRepo extends JpaRepository<Issue, Long> {
    
}
