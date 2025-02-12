package com.proFightingFicAcademy.repository;

import com.proFightingFicAcademy.model.EmployeeRole;
import com.proFightingFicAcademy.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    List<EmployeeRole> findAllByState(State state);
    Optional<EmployeeRole> findByIdAndState(int id, State state);
}
