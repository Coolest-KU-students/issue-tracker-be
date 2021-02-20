package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.IssueStep;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuesStepsRepo extends JpaRepository<IssueStep, Long> {

}
