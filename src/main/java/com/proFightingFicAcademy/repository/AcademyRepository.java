package com.proFightingFicAcademy.repository;

import com.proFightingFicAcademy.model.Academy;
import com.proFightingFicAcademy.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Integer> {
    List<Academy> findAllByState(State state);
    Optional<Academy> findByIdAndState(int id, State state);
}
