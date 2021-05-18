package com.kustudents.issuetracker.controller;

import com.kustudents.issuetracker.model.entity.Issue;
import com.kustudents.issuetracker.model.IssueRead;
import com.kustudents.issuetracker.model.entity.User;
import com.kustudents.issuetracker.service.IssuesService;
import com.kustudents.issuetracker.service.IssuesReadService;
import com.kustudents.issuetracker.service.factory.IssueFactory;
import com.kustudents.issuetracker.utility.DefaultPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issues")
public class IssuesController {

    private final IssuesService issuesService;
    private final IssuesReadService issuesReadService;
    private final IssueFactory issueFactory;

    private static class MandatoryFields {
        public String name;
        public String description;
        public int importance;
    }

    @GetMapping("/")
    public Iterable<IssueRead> getAllIssues() {
        return issuesReadService.getAllIssues();
    }

    @GetMapping("/data")
    public Page<IssueRead> getPaginatedIssues(@RequestParam(defaultValue = DefaultPagination.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DefaultPagination.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(defaultValue = "id") String orderBy, @RequestParam(defaultValue = "1") Boolean ascending,
            @RequestParam(defaultValue = "1") Boolean hideClosed,
            @RequestParam(defaultValue = "0") Boolean showCreatedByUser,
            @RequestParam(defaultValue = "0") Boolean showIssuesWhereUserIsResponsible) {
        return issuesReadService.getAllIssuesPaginatedAndFiltered(hideClosed, showCreatedByUser,
                showIssuesWhereUserIsResponsible, page, size, orderBy, ascending);
    }

    @GetMapping("/{id}")
    public IssueRead getIssueById(@PathVariable("id") Long id) {
        return issuesReadService.getIssueById(id);
    }

    @PostMapping("/")
    public Long createIssue(@RequestBody MandatoryFields fields) {
        Issue issue = issuesService
                .createIssue(issueFactory.createIssue(fields.name, fields.description, fields.importance));
        return issue.getId();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateIssue(@PathVariable("id") Long id, @RequestBody MandatoryFields updatedData) {
        issuesService.updateIssue(id, updatedData.name, updatedData.description, updatedData.importance);
        return new ResponseEntity<>("Issue successfully updated", HttpStatus.CREATED);
    }

}
