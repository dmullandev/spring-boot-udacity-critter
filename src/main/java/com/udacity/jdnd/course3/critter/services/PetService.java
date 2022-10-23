package com.udacity.jdnd.course3.critter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;
    private final UserService userService;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository, UserService userService) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    public Pet savePet(PetDTO petDTO) {
        Customer owner = userService.getCustomerById(petDTO.getOwnerId());

        Pet newPet = convertDTOToPet(petDTO);

        newPet.setOwner(owner);

        Pet savedPet = petRepository.save(newPet);

        Customer owner2 = savedPet.getOwner();
        owner2.addPet(savedPet);

        customerRepository.save(owner2);

        return savedPet;
    }

    public Optional<Pet> getPetById(long petId) {
        return petRepository.findById(petId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }

    public Pet convertDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        return pet;
    }
}
