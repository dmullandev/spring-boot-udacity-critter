package com.udacity.jdnd.course3.critter.entities;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Dog extends Pet {

    public Dog(String name, String colour, String favouriteFood) {
        super(name, colour, favouriteFood);
    }

    public Dog() {
        super(null, null, null);
    }
}
