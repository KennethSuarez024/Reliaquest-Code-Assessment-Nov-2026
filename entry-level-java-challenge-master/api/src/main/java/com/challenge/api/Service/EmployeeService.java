package com.challenge.api.service;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service layer for managing employees.
 * This class should handle all business logic and interactions with the data layer.
 */
@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    // creates a hashmap to store employees in the absence of a persistance layer
    private final Map<UUID, Employee> employees;
    // constructor for EmployeeService
    public EmployeeService() {
        this.employees = new ConcurrentHashMap<>();
    }

    /**
     * Method to be called from the controller to turn the map of employees
     * into a list.
     * returns a list of all currently declared employees
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(this.employees.values());
    }

    /**
     * Method to be called from the controller to send the Employee with the given UUID
     * returns the employee if found, logs the error and throws exception otherwise.
     */
    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = this.employees.get(uuid);

        if (employee == null) {
            logger.warn("Employee was not found for UUID {}", uuid);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return employee;
    }

    /**
     * Method to be called from the controller Create a new employee and
     * add them to the map of declared employees
     * Returns the created employee
     */
    public Employee createEmployee(EmployeeRequest request) {
        this.validateEmployee(request);

        request.setUuid(UUID.randomUUID());
        this.employees.put(request.getUuid(), request);

        logger.info("Created employee with UUID {}", request.getUuid());
        return request;
    }

    /**Method used to perform input validation when
     * a new employee is created
     */
    private void validateEmployee(EmployeeRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is required");
        }

        if (request.getFirstName() == null || request.getFirstName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required");
        }

        if (request.getLastName() == null || request.getLastName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name is required");
        }

        if (request.getSalary() == null || request.getSalary() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salary is required and cannot be negative");
        }

        if (request.getAge() == null || request.getAge() < 18) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Age is required and must be at least 18");
        }

        if (request.getJobTitle() == null || request.getJobTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Job title is required");
        }

        if (request.getEmail() == null
                || request.getEmail().isBlank()
                || !request.getEmail().contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A valid email is required");
        }

        if (request.getContractHireDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contract hire date is required");
        }

        if (request.getContractTerminationDate() != null
                && request.getContractTerminationDate().isBefore(request.getContractHireDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Termination date cannot be before the hire date");
        }
    }
}
