package com.kustudents.issuetracker.service;

import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.repository.IssuesStepsRepository;
import com.kustudents.issuetracker.service.factory.IssueStepFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssuesStepsService {

    private final IssuesStepsRepository issuesStepsRepository;
    private final IssueStepFactory issueStepFactory;

    public void createFirstIssueStep(Issue issue){
        //TODO: map logged in username
        issuesStepsRepository.save(issueStepFactory.createFirstIssueStep("root", issue));
    }

}
