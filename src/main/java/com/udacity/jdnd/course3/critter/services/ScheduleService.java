package com.udacity.jdnd.course3.critter.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PetRepository petRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
    }

    public Schedule saveSchedule(ScheduleDTO scheduleDTO) {
        Schedule newSchedule = new Schedule();

        BeanUtils.copyProperties(scheduleDTO, newSchedule);

        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return savedSchedule;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPetId(Long petId) {
        return scheduleRepository.findAllByPetIds(petId);
    }

    public List<Schedule> getScheduleForEmployeeId(Long employeeId) {
        return scheduleRepository.findAllByEmployeeIds(employeeId);
    }

    public List<Schedule> getScheduleForCustomerId(Long customerId) {
        List<Pet> petList = petRepository.findAllByOwnerId(customerId);

        return new ArrayList<Schedule>();
    }

}
