package com.kustudents.issuetracker.controller;

import com.kustudents.issuetracker.model.IssueStepRead;
import com.kustudents.issuetracker.model.entity.IssueStep;
import com.kustudents.issuetracker.model.entity.Step;
import com.kustudents.issuetracker.service.IssuesStepsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issuesteps/")
public class IssuesStepsController {

    private final IssuesStepsService issuesStepsService;

    @GetMapping("/{id}")
    public List<IssueStepRead> getStepsByIssueId(@PathVariable("id") Long id) {
        return issuesStepsService.getStepsListByIssueId(id);
    }

}
