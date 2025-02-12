package com.proFightingFicAcademy.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private int id;

    @Column(name = "state_description")
    private String description;

    @Column(name = "state_createdAt")
    private String createdAt;

    @Column(name = "state_updatedAt")
    private String updatedAt;


    //constructor
    public State() {
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

}
