package com.ust.budget.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Double amount;

    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;

    private LocalDate createdDate;

    private LocalTime createdTime;

    @PrePersist
    private void onCreate(){
        this.createdDate = LocalDate.now();
        this.createdTime = LocalTime.now();
    }
}