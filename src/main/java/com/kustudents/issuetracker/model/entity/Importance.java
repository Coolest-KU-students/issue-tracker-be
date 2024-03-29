package com.kustudents.issuetracker.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity(name = "viw_Importances")
public class Importance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sort_order")
    @OrderColumn
    private Long sortOrder;

    @NotBlank(message = "All Importances need to be named")
    @Column(name = "name")
    private String name;

    @Embedded
    private GlobalEntity GE = new GlobalEntity();

    @PrePersist
    @PreUpdate
    public void UpdateAudit() {
        GE.OnUpdateAudit();
    }

}
