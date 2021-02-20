package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.repository.IssuesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssuesService {

    private final IssuesRepo issuesRepo;
    private final IssuesStepsService issuesStepsService;

    public Issue createIssue(Issue providedIssue){
        Issue savedIssue = issuesRepo.saveAndFlush(providedIssue);
        issuesStepsService.createFirstIssueStep(savedIssue);
        return savedIssue;
    }

}
