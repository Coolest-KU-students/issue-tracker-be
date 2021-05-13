package com.kustudents.issuetracker.service;

import com.kustudents.issuetracker.exceptions.ResourceNotFoundException;
import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.repository.IssuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssuesService {

    private final IssuesRepository issuesRepository;
    private final IssuesStepsService issuesStepsService;

    public Optional<Issue> getIssueById(Long id){ return issuesRepository.findById(id); }

    public Issue createIssue(Issue providedIssue){
        Issue savedIssue = issuesRepository.saveAndFlush(providedIssue);
        issuesStepsService.createFirstIssueStep(savedIssue);
        return savedIssue;
    }

    public void updateIssue(Long id, String name, String description, int importance){
        Issue issue = issuesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found with id = " + id));

        issue.setName(name);
        issue.setDescription(description);
        issue.setImportance(importance);

        issuesRepository.save(issue);
    }

}
