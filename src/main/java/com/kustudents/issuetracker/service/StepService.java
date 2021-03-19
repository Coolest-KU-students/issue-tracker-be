package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.Step;
import com.kustudents.issuetracker.repository.StepRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StepService {
    private final StepRepository stepRepository;

    public Step createStep(Step step){
       return stepRepository.save(step);
    }

    public void createStep(String stepName){
        long stepCount = stepRepository.count();
        Step step = new Step();
        step.setName(stepName);
        step.setSortOrder(stepCount + 1);
        stepRepository.save(step);
     }

    public List<Step> getStepsList(){
        return stepRepository.findAll(Sort.by("sortOrder"));
    }

    public Step getStepById(Long id){
        return stepRepository.findById(id).orElseThrow();
    }

    public void rewriteSteps(List<Step> steps){
        steps.forEach(this::updateStep);
    }

    public void deleteStepById(Long id){
        if(stepRepository.count() == 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "At least 1 step must remain");
        }
        Step stepToDelete = stepRepository.findById(id).orElseThrow();
        List<Step> steps = stepRepository.findAll();
        steps.removeIf(step -> step.getSortOrder().compareTo(stepToDelete.getSortOrder())<=0);//Removes all steps that have a lower step order
        steps.forEach(step -> {step.setSortOrder(step.getSortOrder()-1);});//decreases the leftover sort orders by 1
        stepRepository.delete(stepToDelete);
        stepRepository.saveAll(steps);
    }

    private void updateStep(Step step){
        Step stepToUpdate = stepRepository.findById(step.getId()).orElseThrow();
        stepToUpdate.setName(step.getName());
        stepToUpdate.setSortOrder(step.getSortOrder());
        stepRepository.save(stepToUpdate);
    }
}

