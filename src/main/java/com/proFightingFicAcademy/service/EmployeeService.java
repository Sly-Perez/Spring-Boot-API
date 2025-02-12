package com.proFightingFicAcademy.service;

import com.proFightingFicAcademy.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(int id);
    Employee add(Employee employee);
    Employee updateById(int id, Employee employee);
    Employee deleteById(int id);
}
