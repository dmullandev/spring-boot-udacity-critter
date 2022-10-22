package com.udacity.jdnd.course3.critter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(PetDTO petDTO) {
        Pet pet = new Pet();

        BeanUtils.copyProperties(petDTO, pet);

        return petRepository.save(pet);
    }

    public Optional<Pet> getPetById(long petId) {
        return petRepository.findById(petId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }
}
