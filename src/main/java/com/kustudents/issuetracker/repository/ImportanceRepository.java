package com.kustudents.issuetracker.repository;

import com.kustudents.issuetracker.model.entity.Importance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportanceRepository extends JpaRepository<Importance, Long> {
    
}
