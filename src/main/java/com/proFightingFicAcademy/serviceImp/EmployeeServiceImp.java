package com.proFightingFicAcademy.serviceImp;

import com.proFightingFicAcademy.exception.ResourceAlreadyExistingException;
import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.*;
import com.proFightingFicAcademy.repository.EmployeeRepository;
import com.proFightingFicAcademy.service.AcademyService;
import com.proFightingFicAcademy.service.EmployeeRoleService;
import com.proFightingFicAcademy.service.EmployeeService;
import com.proFightingFicAcademy.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StateService stateService;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private EmployeeRoleService employeeRoleService;

    @Override
    public List<Employee> getAll() {
        State state = stateService.getById(1);
        return employeeRepository.findAllByState(state);
    }

    @Override
    public Employee getById(int id) {
        State state = stateService.getById(1);
        return employeeRepository.findByIdAndState(id, state).orElseThrow(()->new ResourceNotFoundException("Employee with id " + id + " was not found"));
    }

    @Override
    public Employee add(Employee employee) {
        if(employee.getId() != 0){
            throw new ResourceAlreadyExistingException("Cannot add an employee with id assigned beforehand");
        }
        employee.setId(null);

        State activeState = stateService.getById(1);
        Academy academy = academyService.getById(employee.getAssignedAcademy().getId());
        Employee supervisor = (employee.getSupervisor().getId() == null) ? null : this.getById(employee.getSupervisor().getId());
        EmployeeRole employeeRole = employeeRoleService.getById(employee.getEmployeeRole().getId());

        Employee foundedEmployee = employeeRepository.findByEmailAndState(employee.getEmail(), activeState);

        if(foundedEmployee != null){
            throw new ResourceAlreadyExistingException("Employee with email " + employee.getEmail() + " already exists");
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);


        employee.setCreatedAt(formattedNow);
        employee.setUpdatedAt(null);
        employee.setState(activeState);
        employee.setSupervisor(supervisor);
        employee.setEmployeeRole(employeeRole);
        employee.setAssignedAcademy(academy);

        employeeRepository.save(employee);

        return employee;
    }


    @Override
    public Employee updateById(int id, Employee employee) {
        if(employee.getId() != 0){
            throw new ResourceAlreadyExistingException("Cannot update an employee with id attached to the body; Just indicate the id through the path");
        }

        Employee updatedEmployee = this.getById(id);

        State activeState = stateService.getById(1);
        Academy assignedAcademy = academyService.getById(employee.getAssignedAcademy().getId());
        Employee supervisor = (employee.getSupervisor().getId() == null) ? null : this.getById(employee.getSupervisor().getId());
        EmployeeRole employeeRole = employeeRoleService.getById(employee.getEmployeeRole().getId());

        Employee foundedEmployee = employeeRepository.findByEmailAndState(employee.getEmail(), activeState);

        if( (foundedEmployee != null)  &&  (foundedEmployee.getId() != updatedEmployee.getId()) ){
            throw new ResourceAlreadyExistingException("Employee with email " + employee.getEmail() + " already exists");
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        updatedEmployee.setFullname(employee.getFullname());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setCountry(employee.getCountry());
        updatedEmployee.setEmployeeRole(employeeRole);
        updatedEmployee.setSupervisor(supervisor);
        updatedEmployee.setAssignedAcademy(assignedAcademy);
        updatedEmployee.setUpdatedAt(formattedNow);

        employeeRepository.save(updatedEmployee);

        return updatedEmployee;
    }

    @Override
    public Employee deleteById(int id) {
        Employee deletedEmployee = this.getById(id);

        State inactiveState = stateService.getById(2);
        deletedEmployee.setState(inactiveState);

        employeeRepository.save(deletedEmployee);

        return deletedEmployee;
    }

}
