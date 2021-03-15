package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository  extends JpaRepository<Step, Long>  {
    
}
