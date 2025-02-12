package com.proFightingFicAcademy.dto;


import com.proFightingFicAcademy.model.Academy;


import java.util.List;

public class AcademyDTO {

    private int id;
    private String address;
    private Float areaInSquareMeters;
    private String createdAt;
    private String updatedAt;


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



    public static AcademyDTO from(Academy academy){
        AcademyDTO academyDTO = new AcademyDTO();

        academyDTO.setId(academy.getId());
        academyDTO.setAddress(academy.getAddress());
        academyDTO.setAreaInSquareMeters(academy.getAreaInSquareMeters());
        academyDTO.setCreatedAt(academy.getCreatedAt());
        academyDTO.setUpdatedAt(academy.getUpdatedAt());

        return academyDTO;
    }
}
