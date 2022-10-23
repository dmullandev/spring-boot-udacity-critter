package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.constants.PetType;

import lombok.Data;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PetType type;

    @Nationalized
    private String name;
    private LocalDate birthDate;
    private String notes;

    @ManyToOne
    private Customer owner;

    public Pet(Long id, PetType type, String name, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Pet() {
    }
}
