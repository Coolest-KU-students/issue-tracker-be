package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.ciwIssues;
import com.kustudents.issuetracker.repository.ciwIssuesRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ciwIssuesService{
    private final ciwIssuesRepo cRepo;

    public ciwIssuesService(ciwIssuesRepo cRepo){
        this.cRepo = cRepo;
    }

    public ciwIssues getIssueByID(Long id){
        return cRepo.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public List<ciwIssues> getAllIssues(){
        return cRepo.findAll();
    }

}
