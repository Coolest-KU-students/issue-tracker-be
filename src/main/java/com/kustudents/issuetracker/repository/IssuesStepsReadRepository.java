package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.IssueStepRead;
import com.kustudents.issuetracker.model.entity.IssueStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesStepsReadRepository extends JpaRepository<IssueStepRead, Long> {

    @Query("SELECT IssueSteps FROM ciw_issues_steps IssueSteps where " +
            "(IssueSteps.issue_id = :id) ORDER BY IssueSteps.completed desc nulls first")
    List<IssueStepRead> findAllByIssueId(Long id);

}
