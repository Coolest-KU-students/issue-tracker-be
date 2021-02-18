package com.kustudents.issuetracker.data.enitity;


import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity(name = "viw_Issues")
@Data
public class viwIssues {
    
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

}
