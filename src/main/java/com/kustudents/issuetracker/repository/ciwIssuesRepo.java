package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.ciwIssues;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ciwIssuesRepo extends JpaRepository<ciwIssues, Long>{
    
}
