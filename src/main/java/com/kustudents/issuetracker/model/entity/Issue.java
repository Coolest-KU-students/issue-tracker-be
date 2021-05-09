package com.kustudents.issuetracker.model.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "viw_Issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "closed")
    private LocalDateTime closed;

    @NotBlank(message = "Enter the name of the Issue")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "importance")
    private int importance;

    @Embedded
    private GlobalEntity GE = new GlobalEntity();

    @PrePersist
    @PreUpdate
    public void UpdateAudit() {
        GE.OnUpdateAudit();
    }
}
