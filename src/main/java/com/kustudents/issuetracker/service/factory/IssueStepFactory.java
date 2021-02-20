package com.kustudents.issuetracker.service.factory;

import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.model.entity.IssueStep;

import org.springframework.stereotype.Service;

@Service
public class IssueStepFactory {

    public IssueStep createFirstIssueStep(String Login, Issue issue) {
        IssueStep issueStep = new IssueStep();
        issueStep.setResponsible(Login);
        issueStep.setIssue(issue);
        issueStep.setStepId(1L);
        issueStep.setComment(issue.getDescription());
        return issueStep;
    }

}
