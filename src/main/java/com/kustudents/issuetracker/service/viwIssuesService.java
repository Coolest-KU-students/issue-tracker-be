package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.viwIssues;
import com.kustudents.issuetracker.repository.viwIssuesRepo;
import com.kustudents.issuetracker.repository.viwIssuesStepsRepo;
import com.kustudents.issuetracker.service.factories.IssuesStepsFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class viwIssuesService{
    private final viwIssuesRepo vRepo;
    private final viwIssuesStepsService vIssuesStepsService;
    public viwIssuesService(viwIssuesRepo vRepo, IssuesStepsFactory iStepsFactory, viwIssuesStepsService vIssuesStepsService){
        this.vRepo = vRepo;
        this.vIssuesStepsService = vIssuesStepsService;
    }

    public viwIssues getIssueByID(Long id){
        return vRepo.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public List<viwIssues> getAllIssues(){
        return vRepo.findAll();
    }

    public viwIssues createIssues(viwIssues vIssues){
        viwIssues viwIssue = vRepo.saveAndFlush(vIssues);
        vIssuesStepsService.createFirstIssueStep( viwIssue);
        return viwIssue;
    }

    public void deleteById(Long id) {
        vRepo.deleteById(id);
    }

}
