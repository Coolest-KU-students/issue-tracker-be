package com.kustudents.issuetracker.controller;


import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kustudents.issuetracker.model.entity.ciwIssues;
import com.kustudents.issuetracker.model.entity.viwIssues;
import com.kustudents.issuetracker.repository.viwIssuesRepo;
import com.kustudents.issuetracker.service.viwIssuesService;
import com.kustudents.issuetracker.service.factories.IssueFactory;
import com.kustudents.issuetracker.service.ciwIssuesService;

@RestController
@RequestMapping("/api/Issues")
public class IssuesController {
    private final viwIssuesService vIssuesService;
    private final ciwIssuesService cIssuesService;
    private final IssueFactory issueFactory;
    public IssuesController(viwIssuesService vIssuesService, ciwIssuesService cIssuesService, IssueFactory issueFactory){
        this.vIssuesService = vIssuesService;
        this.cIssuesService = cIssuesService;
        this.issueFactory = issueFactory;
    }

    private static class MandatoryFields{
       public String name;
       public String description;
       public int importance;

    }

    @GetMapping("/")
	@CrossOrigin(origins = "*")
    public List<ciwIssues> getAll() {
        return cIssuesService.getAllIssues();
    }

    @GetMapping("/{id}")
    public ciwIssues getIssues(@PathVariable("id") Long id) {
        return cIssuesService.getIssueByID(id);
    }
        //TODO: return ID to relink to the Issue page
    @PostMapping("/")
    public void createIssue(@RequestBody MandatoryFields mFields){
        vIssuesService.createIssues(issueFactory.createIssue(mFields.name, mFields.description, mFields.importance));

    }

  
}
