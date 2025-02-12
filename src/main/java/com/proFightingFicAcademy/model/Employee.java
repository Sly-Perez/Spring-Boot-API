package com.proFightingFicAcademy.model;

import com.proFightingFicAcademy.dto.EmployeeDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "employee_fullname")
    private String fullname;

    @Column(name = "employee_email")
    private String email;

    @Column(name = "employee_country")
    private String country;

    @Column(name = "employee_createdAt")
    private String createdAt;

    @Column(name = "employee_updatedAt")
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "employee_role")
    private EmployeeRole employeeRole;

    @ManyToOne
    @JoinColumn(name = "employee_supervisor")
    private Employee supervisor;

    @ManyToOne
    @JoinColumn(name = "employee_assignedAcademy")
    private Academy assignedAcademy;

    @ManyToOne
    @JoinColumn(name = "employee_state")
    private State state;


    //constructor
    public Employee() {
        //empty constructor
    }

    //getters and setters
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getFullname() {return fullname;}
    public void setFullname(String fullname) {this.fullname = fullname;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}

    public String getCreatedAt() {return createdAt;}
    public void setCreatedAt(String createdAt) {this.createdAt = createdAt;}

    public String getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(String updatedAt) {this.updatedAt = updatedAt;}

    public EmployeeRole getEmployeeRole() {return employeeRole;}
    public void setEmployeeRole(EmployeeRole employeeRole) {this.employeeRole = employeeRole;}

    public Employee getSupervisor() {return supervisor;}
    public void setSupervisor(Employee supervisor) {this.supervisor = supervisor;}

    public Academy getAssignedAcademy() {return assignedAcademy;}
    public void setAssignedAcademy(Academy assignedAcademy) {this.assignedAcademy = assignedAcademy;}

    public State getState() {return state;}
    public void setState(State state) {this.state = state;}


    //method
    public static Employee from(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());
        employee.setFullname(employeeDTO.getFullname());
        employee.setEmail(employeeDTO.getEmail());
        employee.setCountry(employeeDTO.getCountry());
        employee.setCreatedAt(employeeDTO.getCreatedAt());
        employee.setUpdatedAt(employeeDTO.getUpdatedAt());

        //employee_role
        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setId(employeeDTO.getEmployeeRoleId());

        employee.setEmployeeRole(employeeRole);
        //employee_supervisor
        Employee supervisor = new Employee();
        supervisor.setId(employeeDTO.getSupervisorId());

        employee.setSupervisor(supervisor);
        //employee_assignedAcademy
        Academy academy = new Academy();
        academy.setId(employeeDTO.getAssignedAcademyId());

        employee.setAssignedAcademy(academy);

        return employee;
    }

}
