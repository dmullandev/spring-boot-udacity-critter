package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Set<Employee> findEmployeeByDaysAvailable(DayOfWeek dayOfWeek);
}
