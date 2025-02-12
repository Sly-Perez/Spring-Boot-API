package com.proFightingFicAcademy.controller;

import com.proFightingFicAcademy.dto.AcademyDTO;
import com.proFightingFicAcademy.dto.StudentDTO;
import com.proFightingFicAcademy.model.Academy;


import com.proFightingFicAcademy.model.Student;

import com.proFightingFicAcademy.service.AcademyService;
import com.proFightingFicAcademy.service.StateService;

import com.proFightingFicAcademy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pff_api/academies")
public class AcademyController {

    @Autowired
    private AcademyService academyService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StateService stateService;

    @GetMapping("/")
    public ResponseEntity<List<AcademyDTO>> getAll(){

        //find all academies
        List<Academy> academies = academyService.getAll();
        //map all academies to academiesDTO
        List<AcademyDTO> academiesDTO = academies.stream().map(AcademyDTO::from).collect(Collectors.toList());
        return new ResponseEntity<>(academiesDTO, HttpStatus.OK);
    }
}
