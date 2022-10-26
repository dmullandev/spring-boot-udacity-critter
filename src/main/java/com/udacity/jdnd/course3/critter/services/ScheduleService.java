package com.udacity.jdnd.course3.critter.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PetService petService;
    private final UserService userService;

    public ScheduleService(ScheduleRepository scheduleRepository, PetService petService, UserService userService) {
        this.scheduleRepository = scheduleRepository;
        this.petService = petService;
        this.userService = userService;
    }

    public Schedule saveSchedule(ScheduleDTO scheduleDTO) {
        Schedule newSchedule = convertScheduleDTOToSchedule(scheduleDTO);

        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return savedSchedule;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPetId(Long petId) {
        return scheduleRepository.findScheduleByPets(petService.getPetById(petId).get());
    }

    public List<Schedule> getScheduleForEmployeeId(Long employeeId) {
        return scheduleRepository.findScheduleByEmployees(userService.getEmployeeById(employeeId).get());
    }

    public List<Schedule> getScheduleForCustomerId(Long customerId) {
        List<Pet> petList = petService.getPetsByOwnerId(customerId);

        List<Schedule> scheduleList = new ArrayList<>();

        petList.forEach(pet -> scheduleList.addAll(scheduleRepository.findScheduleByPets(pet)));

        return scheduleList;
    }

    public Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();

        List<Employee> employees = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();

        if (employeeIds != null) {
            employeeIds.forEach(employeeId -> {
                try {
                    employees.add(userService.getEmployeeById(employeeId).get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        if (petIds != null) {
            petIds.forEach(petId -> {
                try {
                    pets.add(petService.getPetById(petId).get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return schedule;
    }

}
