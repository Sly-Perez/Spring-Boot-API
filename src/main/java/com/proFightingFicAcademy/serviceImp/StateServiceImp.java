package com.proFightingFicAcademy.serviceImp;

import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.State;
import com.proFightingFicAcademy.repository.StateRepository;
import com.proFightingFicAcademy.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImp implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public State getById(int id) {
        return stateRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("State with id " + id + " was not found"));
    }
}
