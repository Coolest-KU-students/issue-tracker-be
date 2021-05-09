package com.kustudents.issuetracker.service;

import com.kustudents.issuetracker.model.IssueStepRead;
import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.model.entity.IssueStep;
import com.kustudents.issuetracker.model.entity.Step;
import com.kustudents.issuetracker.repository.IssuesStepsReadRepository;
import com.kustudents.issuetracker.repository.IssuesStepsRepository;
import com.kustudents.issuetracker.service.factory.IssueStepFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssuesStepsService {

    private final IssuesStepsRepository issuesStepsRepository;
    private final IssuesStepsReadRepository issuesStepsReadRepository;
    private final IssueStepFactory issueStepFactory;
    private final AuthenticationService authenticationService;

    public void createFirstIssueStep(Issue issue){
        issuesStepsRepository.save(issueStepFactory.createFirstIssueStep(authenticationService.getLoggedInUserLogin(), issue));
    }

    public List<IssueStepRead> getStepsListByIssueId(Long id){ return issuesStepsReadRepository.findAllByIssueId(id); }

}
