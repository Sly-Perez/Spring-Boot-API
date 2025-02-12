package com.proFightingFicAcademy.serviceImp;

import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.Academy;
import com.proFightingFicAcademy.model.State;
import com.proFightingFicAcademy.repository.AcademyRepository;

import com.proFightingFicAcademy.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademyServiceImp implements AcademyService {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private StateServiceImp stateService;

    @Override
    public List<Academy> getAll() {
        State state = stateService.getById(1);
        return academyRepository.findAllByState(state);
    }

    @Override
    public Academy getById(int id) {
        State state = stateService.getById(1);
        return academyRepository.findByIdAndState(id, state).orElseThrow(()->new ResourceNotFoundException("Academy with id " + id + " was not found"));
    }
}
