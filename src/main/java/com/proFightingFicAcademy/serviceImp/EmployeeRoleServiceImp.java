package com.proFightingFicAcademy.serviceImp;

import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.EmployeeRole;
import com.proFightingFicAcademy.model.State;
import com.proFightingFicAcademy.repository.EmployeeRoleRepository;
import com.proFightingFicAcademy.service.EmployeeRoleService;
import com.proFightingFicAcademy.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeRoleServiceImp implements EmployeeRoleService {

    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    private StateService stateService;

    @Override
    public List<EmployeeRole> getAll() {
        State state = stateService.getById(1);
        return employeeRoleRepository.findAllByState(state);
    }

    @Override
    public EmployeeRole getById(int id) {
        State state = stateService.getById(1);
        return employeeRoleRepository.findByIdAndState(id, state).orElseThrow(()->new ResourceNotFoundException("Employee Role with id " + id + " was not found"));
    }
}
