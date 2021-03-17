package com.kustudents.issuetracker.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "viw_Steps")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "sort_order")
    private Long sortOrder;

    @NotBlank(message = "All Steps need to be named")
    @Column(name = "name")
    private String name;
}
