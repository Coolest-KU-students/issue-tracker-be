package com.kustudents.issuetracker.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.kustudents.issuetracker.model.entity.Step;
import com.kustudents.issuetracker.service.StepService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/steps")
public class StepController {
    
    private final StepService stepService;

    @GetMapping("/")
    public List<Step> getAllSteps(){
      return stepService.getStepsList();
    } 

    @GetMapping("/{id}")
    public Step getStepById(@PathVariable("id") Long id) {
        return stepService.getStepById(id);
    }

    @PostMapping("/")
    public void createNewStep(@RequestBody String stepName){
        stepService.createStep(stepName);
    }

    @PutMapping("/")
    public void updateSteps(@RequestBody List<Step> steps){
        stepService.rewriteSteps(steps);
    }

    @DeleteMapping("/{id}")
    public void deleteStep(@PathVariable("id") Long id) {
        stepService.deleteStepById(id);
    }

}
