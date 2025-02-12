package com.proFightingFicAcademy.repository;

import com.proFightingFicAcademy.model.Employee;
import com.proFightingFicAcademy.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByState(State state);
    Optional<Employee> findByIdAndState(int id, State state);
    Employee findByEmailAndState(String email, State state);
}
