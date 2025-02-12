package com.proFightingFicAcademy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_roles")
public class EmployeeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeRole_id")
    private int id;

    @Column(name = "employeeRole_description")
    private String description;

    @Column(name = "employeeRole_createdAt")
    private String createdAt;

    @Column(name = "employeeRole_updatedAt")
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "employeeRole_state")
    private State state;


    //constructor
    public EmployeeRole() {
        //empty constructor
    }


    //getters and setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getCreatedAt() {return createdAt;}
    public void setCreatedAt(String createdAt) {this.createdAt = createdAt;}

    public String getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(String updatedAt) {this.updatedAt = updatedAt;}

    public State getState() {return state;}
    public void setState(State state) {this.state = state;}

}
