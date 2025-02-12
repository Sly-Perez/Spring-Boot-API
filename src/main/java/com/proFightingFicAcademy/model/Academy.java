package com.proFightingFicAcademy.model;

import jakarta.persistence.*;


@Entity
@Table(name = "academies")
public class Academy{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academy_id")
    private int id;

    @Column(name = "academy_address")
    private String address;

    @Column(name = "academy_areaInSquareMeters")
    private Float areaInSquareMeters;

    @Column(name = "academy_createdAt")
    private String createdAt;

    @Column(name = "academy_updatedAt")
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "academy_state")
    private State state;


    //constructor
    public Academy() {
        //empty constructor
    }

    //getters and setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public Float getAreaInSquareMeters() {return areaInSquareMeters;}
    public void setAreaInSquareMeters(Float areaInSquareMeters) {this.areaInSquareMeters = areaInSquareMeters;}

    public String getCreatedAt() {return createdAt;}
    public void setCreatedAt(String createdAt) {this.createdAt = createdAt;}

    public String getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(String updatedAt) {this.updatedAt = updatedAt;}

    public State getState() {return state;}
    public void setState(State state) {this.state = state;}

}
