package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.udacity.jdnd.course3.critter.constants.EmployeeSkill;

import lombok.Data;

@Entity
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Long> employeeIds;

    @ElementCollection
    private List<Long> petIds;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;
}
