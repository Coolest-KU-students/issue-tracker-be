package com.kustudents.issuetracker.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity(name = "viw_Issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime closed;

    @Column
    @NotBlank(message = "Enter the name of the Issue")
    private String name;

    @Column
    private String description;

    @Column
    @NotNull
    private int importance;

}
