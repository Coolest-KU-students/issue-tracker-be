package com.kustudents.issuetracker.service.factories;

import com.kustudents.issuetracker.model.entity.viwIssues;
import com.kustudents.issuetracker.model.entity.viwIssuesSteps;

import org.springframework.stereotype.Service;
@Service
public class IssuesStepsFactory {

    public viwIssuesSteps createFirstIssueStep(String Login, viwIssues vIssues, String comment) {
        viwIssuesSteps vStep = new viwIssuesSteps();
        vStep.setResponsible(Login);
        vStep.setVIssues(vIssues);
        vStep.setStepId(1);
        vStep.setComment(comment);
        return vStep;
    }
}
