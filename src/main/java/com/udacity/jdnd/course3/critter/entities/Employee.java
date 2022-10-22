package com.udacity.jdnd.course3.critter.entities;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    // @OneToMany // many skills can belong to one employee
    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USER_SKILL", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "SKILLS")
    private Set<EmployeeSkill> skills;

}
