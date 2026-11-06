package com.challenge.api.model;

import java.time.Instant;
import java.util.UUID;

public class EmployeeRequest implements Employee {
    // class variables
    private UUID uuid;
    private String firstName;
    private String lastName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    // employee getter and setter methods
    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public void setFullName(String name) {
        String[] names = name.split(" ");
        if (names.length == 2) {
            this.setFirstName(names[0]);
            this.setLastName(names[1]);
        }
    }

    @Override
    public Integer getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getJobTitle() {
        return this.jobTitle;
    }

    @Override
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Instant getContractHireDate() {
        return this.contractHireDate;
    }

    @Override
    public void setContractHireDate(Instant date) {
        this.contractHireDate = date;
    }

    @Override
    public Instant getContractTerminationDate() {
        return this.contractTerminationDate;
    }

    @Override
    public void setContractTerminationDate(Instant date) {
        this.contractTerminationDate = date;
    }
}
