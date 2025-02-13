package com.proFightingFicAcademy.service;

import com.proFightingFicAcademy.model.Academy;
import com.proFightingFicAcademy.model.State;

import java.util.List;

public interface AcademyService {
    //we'll get Academies with state.id = 1: "Active"
    List<Academy> getAll();
    Academy getById(int id);
    Academy add(Academy academy);
    Academy updateById(int id, Academy academy);
}
