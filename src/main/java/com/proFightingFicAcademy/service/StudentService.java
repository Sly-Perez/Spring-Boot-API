package com.proFightingFicAcademy.service;

import com.proFightingFicAcademy.model.Academy;

import com.proFightingFicAcademy.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    List<Student> getAllByAcademy(Academy academy);
    Student getById(int id);
    Student add(Student student);
    Student deleteById(int id);
    Student updateById(int id, Student student);
}
