package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.model.entity.IssueStep;
import com.kustudents.issuetracker.repository.IssuesStepsRepo;
import com.kustudents.issuetracker.service.factory.IssueStepFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssuesStepsService {

    private final IssuesStepsRepo issuesStepsRepo;
    private final IssueStepFactory issueStepFactory;

    public void createFirstIssueStep(Issue issue){
        //TODO: map logged in username
        issuesStepsRepo.save(issueStepFactory.createFirstIssueStep("root", issue));
    }

}
