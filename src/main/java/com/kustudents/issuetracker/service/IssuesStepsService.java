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
    private final AuthenticationService authenticationService;

    public void createFirstIssueStep(Issue issue){
        issuesStepsRepository.save(issueStepFactory.createFirstIssueStep(authenticationService.getLoggedInUserLogin(), issue));
    }

}
