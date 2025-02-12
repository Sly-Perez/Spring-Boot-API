package com.proFightingFicAcademy.dto;


import com.proFightingFicAcademy.model.EmployeeRole;

public class EmployeeRoleDTO {

    private int id;
    private String description;
    private String createdAt;
    private String updatedAt;


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getCreatedAt() {return createdAt;}
    public void setCreatedAt(String createdAt) {this.createdAt = createdAt;}

    public String getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(String updatedAt) {this.updatedAt = updatedAt;}


    //method
    public static EmployeeRoleDTO from(EmployeeRole employeeRole){
        EmployeeRoleDTO employeeRoleDTO = new EmployeeRoleDTO();

        employeeRoleDTO.setId(employeeRole.getId());
        employeeRoleDTO.setDescription(employeeRole.getDescription());
        employeeRoleDTO.setCreatedAt(employeeRole.getCreatedAt());
        employeeRoleDTO.setUpdatedAt(employeeRole.getUpdatedAt());

        return employeeRoleDTO;
    }
}
