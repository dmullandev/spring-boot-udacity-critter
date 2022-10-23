package com.udacity.jdnd.course3.critter.entities;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.constants.EmployeeSkill;

import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Nationalized
    private String name;
    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> skills = new HashSet<>();

    @ElementCollection(targetClass = DayOfWeek.class)
    private Set<DayOfWeek> daysAvailable = new HashSet<>();

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

}
