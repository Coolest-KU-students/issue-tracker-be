package com.kustudents.issuetracker.model.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "viw_Importance")
public class Importance {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "importance_Sort_ID")
    private Long id;

    @NotBlank(message = "All Importances need to be named")
    @Column(name = "name")
    private LocalDateTime name;

}
