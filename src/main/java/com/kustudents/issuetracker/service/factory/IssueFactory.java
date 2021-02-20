package com.kustudents.issuetracker.service.factory;

import com.kustudents.issuetracker.model.entity.Issue;

import org.springframework.stereotype.Service;

@Service
public class IssueFactory {

    public Issue createIssue(String name, String description, int importance) {
        Issue issue = new Issue();
        issue.setName(name);
        issue.setDescription(description);
        issue.setImportance(importance);
        return issue;
    }

}
