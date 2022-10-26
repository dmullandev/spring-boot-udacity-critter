package com.udacity.jdnd.course3.critter.services;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
@Transactional
public class UserService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;

    public UserService(CustomerRepository customerRepository, EmployeeRepository employeeRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        BeanUtils.copyProperties(customerDTO, customer);

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.getOne(customerId);
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDTO, employee);

        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Customer getOwnerByPetId(Long petId) {
        Pet pet = petRepository.getOne(petId);

        return customerRepository.findCustomerByPets(pet);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);

        employee.setDaysAvailable(daysAvailable);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        Set<Employee> employeesWithAvailability = employeeRepository.findEmployeeByDaysAvailable(employeeRequestDTO.getDate().getDayOfWeek());

        List<Employee> employeesWithAvailabilityAndSkills = employeesWithAvailability
                                                                                     .stream()
                                                                                     .filter(employee -> employee.getSkills()
                                                                                                                 .containsAll(employeeRequestDTO.getSkills()))
                                                                                     .collect(Collectors.toList());

        return employeesWithAvailabilityAndSkills;
    }
}
