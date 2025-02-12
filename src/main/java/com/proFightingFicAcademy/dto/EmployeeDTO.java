package com.proFightingFicAcademy.dto;

import com.proFightingFicAcademy.model.Employee;

public class EmployeeDTO {

    private int id;
    private String fullname;
    private String email;
    private String country;
    private String createdAt;
    private String updatedAt;
    private int employeeRoleId;
    private String employeeRoleDescription;
    private Integer supervisorId;
    private int assignedAcademyId;


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

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

    public int getEmployeeRoleId() {return employeeRoleId;}
    public void setEmployeeRoleId(int employeeRoleId) {this.employeeRoleId = employeeRoleId;}

    public String getEmployeeRoleDescription() {return employeeRoleDescription;}
    public void setEmployeeRoleDescription(String employeeRoleDescription) {this.employeeRoleDescription = employeeRoleDescription;}

    public Integer getSupervisorId() {return supervisorId;}
    public void setSupervisorId(Integer supervisorId) {this.supervisorId = supervisorId;}

    public int getAssignedAcademyId() {return assignedAcademyId;}
    public void setAssignedAcademyId(int assignedAcademyId) {this.assignedAcademyId = assignedAcademyId;}


    //method
    public static EmployeeDTO from(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setFullname(employee.getFullname());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setCountry(employee.getCountry());
        employeeDTO.setCreatedAt(employee.getCreatedAt());
        employeeDTO.setUpdatedAt(employee.getUpdatedAt());
        employeeDTO.setEmployeeRoleId(employee.getEmployeeRole().getId());
        employeeDTO.setEmployeeRoleDescription(employee.getEmployeeRole().getDescription());

        if(employee.getSupervisor() == null){
            employeeDTO.setSupervisorId(null);
        }
        else{
            employeeDTO.setSupervisorId(employee.getSupervisor().getId());
        }

        employeeDTO.setAssignedAcademyId(employee.getAssignedAcademy().getId());

        return employeeDTO;
    }
}
