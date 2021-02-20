package com.kustudents.issuetracker.service.factories;

import com.kustudents.issuetracker.model.entity.viwIssues;

import org.springframework.stereotype.Service;
@Service
public class IssueFactory {

    public viwIssues createIssue(String name, String description, int importance) {
        viwIssues issue = new viwIssues();
        issue.setName(name);
        issue.setDescription(description);
        issue.setImportance(importance);
        return issue;
    }
}
