package com.proFightingFicAcademy.repository;

import com.proFightingFicAcademy.model.Academy;
import com.proFightingFicAcademy.model.Student;
import com.proFightingFicAcademy.model.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    //we'll get Students with state.id = 1: "Active"
    List<Student> findAllByState(State state);
    List<Student> findAllByAcademyAndState(Academy academy, State state);
    Optional<Student> findByIdAndState(int id, State state);
    Student findByEmailAndState(String email, State state);
    //we won't use the "deleteById" method provided by Our repository because we won't delete any rows, but we'll just change its state

}
