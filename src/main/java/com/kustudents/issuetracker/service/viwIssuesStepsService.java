package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.viwIssues;
import com.kustudents.issuetracker.model.entity.viwIssuesSteps;
import com.kustudents.issuetracker.repository.viwIssuesStepsRepo;
import com.kustudents.issuetracker.service.factories.IssuesStepsFactory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class viwIssuesStepsService{
    private final viwIssuesStepsRepo vStepsRepo;

    private final IssuesStepsFactory iStepsFactory;

    public viwIssuesStepsService(viwIssuesStepsRepo vStepsRepo, IssuesStepsFactory iStepsFactory){
        this.vStepsRepo = vStepsRepo;
        this.iStepsFactory = iStepsFactory;
    }

    public viwIssuesSteps getIssueStepByID(Long id){
        return vStepsRepo.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public List<viwIssuesSteps> getAllIssueStepsByIssueId(){
        return vStepsRepo.findAll();
    }

    public viwIssuesSteps createIssueSteps(viwIssuesSteps vIssuesStep){
        return vStepsRepo.save(vIssuesStep);
    }

    public void createFirstIssueStep(viwIssues vIssues){
        //TODO: map actual login... somehow
        vStepsRepo.save(iStepsFactory.createFirstIssueStep("root", vIssues, vIssues.getDescription()));
        System.out.println(vIssues);
    }

    public void deleteById(Long id) {
        vStepsRepo.deleteById(id);
    }

}
