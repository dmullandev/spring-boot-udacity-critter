package com.udacity.jdnd.course3.critter.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String colour;
    private String favouriteFood;

    public Pet(String name, String colour, String favouriteFood) {
        this.name = name;
        this.colour = colour;
        this.favouriteFood = favouriteFood;
    }
}
