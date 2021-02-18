package com.kustudents.issuetracker.data.repo;

import com.kustudents.issuetracker.data.enitity.viwIssues;

import org.springframework.data.jpa.repository.JpaRepository;

public interface viwIssuesRepo extends JpaRepository<viwIssues, Long>{
    
}
