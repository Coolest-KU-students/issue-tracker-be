package com.kustudents.issuetracker.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity(name = "viw_Issues")
public class Issue {
    
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDateTime closed;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int importance;

}
