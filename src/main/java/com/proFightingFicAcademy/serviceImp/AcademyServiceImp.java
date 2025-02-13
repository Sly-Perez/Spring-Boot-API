package com.proFightingFicAcademy.serviceImp;

import com.proFightingFicAcademy.exception.ResourceAlreadyExistingException;
import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.Academy;
import com.proFightingFicAcademy.model.State;
import com.proFightingFicAcademy.repository.AcademyRepository;

import com.proFightingFicAcademy.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public Academy add(Academy academy) {
        if(academy.getId() != 0){
            throw new ResourceAlreadyExistingException("Cannot add an academy with id assigned beforehand");
        }
        academy.setId(null);

        State activeState = stateService.getById(1);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        academy.setCreatedAt(formattedNow);
        academy.setUpdatedAt(null);
        academy.setState(activeState);

        academyRepository.save(academy);

        return academy;
    }

    @Override
    public Academy updateById(int id, Academy academy) {
        if(academy.getId() != 0){
            throw new ResourceAlreadyExistingException("Cannot update an academy with id attached to the body; Just indicate the id through the path");
        }
        Academy updatedAcademy = this.getById(id);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        updatedAcademy.setAddress(academy.getAddress());
        updatedAcademy.setAreaInSquareMeters(academy.getAreaInSquareMeters());
        updatedAcademy.setUpdatedAt(formattedNow);

        academyRepository.save(updatedAcademy);

        return updatedAcademy;
    }


}
