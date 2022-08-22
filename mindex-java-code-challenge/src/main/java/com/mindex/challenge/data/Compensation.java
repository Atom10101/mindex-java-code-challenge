package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.time.Instant;

public class Compensation {
    private Employee employee;
    private String salary;
    private Instant effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSalary() {
        return this.salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Instant getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Compensation)) {
            return false;
        }
        Compensation compensation = (Compensation) o;
        return Objects.equals(employee, compensation.employee) 
            && Objects.equals(salary, compensation.salary) 
            && Objects.equals(effectiveDate, compensation.effectiveDate);
    }
}
