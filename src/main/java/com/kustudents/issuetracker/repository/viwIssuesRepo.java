package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.viwIssues;

import org.springframework.data.jpa.repository.JpaRepository;

public interface viwIssuesRepo extends JpaRepository<viwIssues, Long>{
    
}
