package com.kustudents.issuetracker.controller;


import java.util.List;

import com.kustudents.issuetracker.model.entity.Issue;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.kustudents.issuetracker.model.entity.IssueRead;
import com.kustudents.issuetracker.service.IssuesService;
import com.kustudents.issuetracker.service.factory.IssueFactory;
import com.kustudents.issuetracker.service.IssuesReadService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Issues")
public class IssuesController {

    private final IssuesService issuesService;
    private final IssuesReadService issuesReadService;
    private final IssueFactory issueFactory;

    @GetMapping("/")
	@CrossOrigin(origins = "*")
    public List<IssueRead> getAllIssues() {
        return issuesReadService.getAllIssues();
    }

    @GetMapping("/{id}")
    public IssueRead getIssueById(@PathVariable("id") Long id) {
        return issuesReadService.getIssueById(id);
    }

    private static class MandatoryFields {
        public String name;
        public String description;
        public int importance;
    }

    @PostMapping("/")
    public int createIssue(@RequestBody MandatoryFields fields){
        Issue issue = issuesService.createIssue(issueFactory.createIssue(fields.name, fields.description, fields.importance));
        return issue.getId();
    }

}
