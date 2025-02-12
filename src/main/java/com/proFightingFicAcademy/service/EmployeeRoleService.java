package com.proFightingFicAcademy.service;

import com.proFightingFicAcademy.model.EmployeeRole;

import java.util.List;

public interface EmployeeRoleService {
    List<EmployeeRole> getAll();
    EmployeeRole getById(int id);
}
