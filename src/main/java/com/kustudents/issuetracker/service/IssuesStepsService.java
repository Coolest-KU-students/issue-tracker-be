package com.kustudents.issuetracker.service;

import com.kustudents.issuetracker.exceptions.ResourceNotFoundException;
import com.kustudents.issuetracker.model.IssueStepRead;
import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.model.entity.IssueStep;
import com.kustudents.issuetracker.model.entity.Step;
import com.kustudents.issuetracker.repository.IssuesRepository;
import com.kustudents.issuetracker.repository.IssuesStepsReadRepository;
import com.kustudents.issuetracker.repository.IssuesStepsRepository;
import com.kustudents.issuetracker.service.factory.IssueStepFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssuesStepsService {

    private final IssuesStepsRepository issuesStepsRepository;
    private final IssuesStepsReadRepository issuesStepsReadRepository;
    private final IssueStepFactory issueStepFactory;
    private final AuthenticationService authenticationService;
    private final IssuesRepository issuesRepository;

    public void createFirstIssueStep(Issue issue){
        issuesStepsRepository.save(issueStepFactory.createFirstIssueStep(AuthenticationService.getLoggedInUserLogin(), issue));
    }

    public List<IssueStepRead> getStepsListByIssueId(Long id){ return issuesStepsReadRepository.findAllByIssueId(id); }

    public void updateStep(Long id, String responsible, String comment){
        IssueStep issueStep = issuesStepsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Issue step not found with id = " + id));

        issueStep.setResponsible(responsible);
        issueStep.setComment(comment);

        issuesStepsRepository.save(issueStep);
    }

    public void closeIssueByIssueStepId(Long id){
        IssueStep issueStep = issuesStepsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Issue step not found with id = " + id));

        issueStep.setCompleted(LocalDateTime.now());

        issuesStepsRepository.save(issueStep);

        Issue issue = issuesRepository.findById(issueStep.getIssue().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Issue step not found with id = " + id));

        issue.setClosed(LocalDateTime.now());

        issuesRepository.save(issue);
    }

    public void closeOldStepAndCreateNewStep (Long oldIssueStepId, Long newStepId) {
        IssueStep issueStep = issuesStepsRepository.findById(oldIssueStepId).orElseThrow(() ->
                new ResourceNotFoundException("Issue step not found with id = " + oldIssueStepId));

        issueStep.setCompleted(LocalDateTime.now());

        issuesStepsRepository.save(issueStep);

        Issue issue = issuesRepository.findById(issueStep.getIssue().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Issue not found with id = " + issueStep.getIssue().getId()));

        issuesStepsRepository.save(issueStepFactory.createIssueStep(AuthenticationService.getLoggedInUserLogin(), issue, newStepId));
    }

}
