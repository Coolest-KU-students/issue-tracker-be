package com.kustudents.issuetracker.service;

import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.repository.IssuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssuesService {

    private final IssuesRepository issuesRepository;
    private final IssuesStepsService issuesStepsService;

    public Issue createIssue(Issue providedIssue){
        Issue savedIssue = issuesRepository.saveAndFlush(providedIssue);
        issuesStepsService.createFirstIssueStep(savedIssue);
        return savedIssue;
    }

}
