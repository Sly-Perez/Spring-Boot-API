package com.proFightingFicAcademy.model;

import com.proFightingFicAcademy.dto.StudentDTO;
import jakarta.persistence.*;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "student_fullname")
    private String fullname;

    @Column(name = "student_email")
    private String email;

    @Column(name = "student_country")
    private String country;

    @Column(name = "student_createdAt")
    private String createdAt;

    @Column(name = "student_updatedAt")
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "student_academy")
    private Academy academy;

    @ManyToOne
    @JoinColumn(name = "student_state")
    private State state;


    //constructor
    public Student() {
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

    public Academy getAcademy() {return academy;}
    public void setAcademy(Academy academy) {this.academy = academy;}

    public State getState() {return state;}
    public void setState(State state) {this.state = state;}



    public static Student from(StudentDTO studentDTO){
        Student student = new Student();

        student.setId(studentDTO.getId());
        student.setFullname(studentDTO.getFullname());
        student.setEmail(studentDTO.getEmail());
        student.setCountry(studentDTO.getCountry());
        student.setCreatedAt(studentDTO.getCreatedAt());
        student.setUpdatedAt(studentDTO.getUpdatedAt());

        //student_academy
        Academy academy = new Academy();
        academy.setId(studentDTO.getAcademyId());

        student.setAcademy(academy);

        return student;
    }
}
