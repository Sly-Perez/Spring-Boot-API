package com.proFightingFicAcademy.dto;

import com.proFightingFicAcademy.model.Student;


public class StudentDTO {
    private int id;
    private String fullname;
    private String email;
    private String country;
    private String createdAt;
    private String updatedAt;
    private int academyId;


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

    public int getAcademyId() {return academyId;}
    public void setAcademyId(int academyId) {this.academyId = academyId;}



    public static StudentDTO from(Student student){
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setFullname(student.getFullname());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setCountry(student.getCountry());
        studentDTO.setCreatedAt(student.getCreatedAt());
        studentDTO.setUpdatedAt(student.getUpdatedAt());
        studentDTO.setAcademyId(student.getAcademy().getId());

        return studentDTO;
    }
}
