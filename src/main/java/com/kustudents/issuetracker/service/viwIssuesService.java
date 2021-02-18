package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.data.enitity.viwIssues;
import com.kustudents.issuetracker.data.repo.viwIssuesRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class viwIssuesService{
    private final viwIssuesRepo vRepo;

    public viwIssuesService(viwIssuesRepo vRepo){
        this.vRepo = vRepo;
    }

    public viwIssues getIssueByID(Long id){
        return vRepo.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public List<viwIssues> getAllIssues(){
        return vRepo.findAll();
    }

    public viwIssues createIssues(viwIssues vIssues){
        return vRepo.save(vIssues);
    }

    public void deleteById(Long id) {
        vRepo.deleteById(id);
    }

}
