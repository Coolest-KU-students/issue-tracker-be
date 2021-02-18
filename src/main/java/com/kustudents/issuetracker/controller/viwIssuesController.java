package com.kustudents.issuetracker.controller;


import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.kustudents.issuetracker.data.enitity.viwIssues;
import com.kustudents.issuetracker.service.viwIssuesService;

@RestController
@RequestMapping("/api/Issues")
public class viwIssuesController {
    private final viwIssuesService vIssuesService;
    
    public viwIssuesController(viwIssuesService vIssuesService){
        this.vIssuesService = vIssuesService;
    }

    @GetMapping("/")
    public List<viwIssues> getAll() {
        return vIssuesService.getAllIssues();
    }

    @GetMapping("/{id}")
    public viwIssues getPatient(@PathVariable("id") Long id) {
        return vIssuesService.getIssueByID(id);
    }

  
}
