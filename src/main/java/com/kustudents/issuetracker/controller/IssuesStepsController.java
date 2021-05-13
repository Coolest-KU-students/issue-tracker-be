package com.kustudents.issuetracker.controller;

import com.kustudents.issuetracker.model.IssueStepRead;
import com.kustudents.issuetracker.model.entity.IssueStep;
import com.kustudents.issuetracker.model.entity.Step;
import com.kustudents.issuetracker.service.IssuesStepsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issuesteps/")
public class IssuesStepsController {

    private final IssuesStepsService issuesStepsService;

    private static class MandatoryFields {
        public String responsible;
        public String comment;
    }

    //Path variable id - issue id
    @GetMapping("/{id}")
    public List<IssueStepRead> getStepsByIssueId(@PathVariable("id") Long id) {
        return issuesStepsService.getStepsListByIssueId(id);
    }

    //Path variable id - issue step id
    @PutMapping("/{id}")
    public ResponseEntity<String> updateIssueStepById(@PathVariable("id") Long id, @RequestBody MandatoryFields updatedData) {
        issuesStepsService.updateStep(id, updatedData.responsible, updatedData.comment);
        return new ResponseEntity<>("Issue step successfully updated", HttpStatus.CREATED);
    }

    //Path variable id - issue step id
    @PostMapping("/{id}")
    public ResponseEntity<String> closeIssueByIssueStepId(@PathVariable("id") Long id) {
        issuesStepsService.closeIssueByIssueStepId(id);
        return new ResponseEntity<>("Issue successfully closed", HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/")
    public ResponseEntity<String> closeOldStepAndCreateNewStep(@RequestParam() Long oldIssueStepId,
                                                               @RequestParam() Long newStepId) {
        issuesStepsService.closeOldStepAndCreateNewStep(oldIssueStepId, newStepId);
        return new ResponseEntity<>("New step successfully created", HttpStatus.CREATED);
    }

}
