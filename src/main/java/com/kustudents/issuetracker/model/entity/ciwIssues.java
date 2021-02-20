package com.kustudents.issuetracker.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity(name = "ciw_Issues")
@Data
public class ciwIssues {
    
    @Column
    @Id
    private int id;

    @Column
    private LocalDateTime closed;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int importance;

    @Column
    private String currentStep;
}
