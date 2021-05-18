package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.IssueStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesStepsRepository extends JpaRepository<IssueStep, Long> {

}
