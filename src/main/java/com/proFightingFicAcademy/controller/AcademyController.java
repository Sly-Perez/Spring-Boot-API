package com.proFightingFicAcademy.controller;

import com.proFightingFicAcademy.dto.AcademyDTO;
import com.proFightingFicAcademy.exception.ResourceAlreadyExistingException;
import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.Academy;


import com.proFightingFicAcademy.response.ResponseHandler;
import com.proFightingFicAcademy.service.AcademyService;
import com.proFightingFicAcademy.service.StateService;

import com.proFightingFicAcademy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getAll(){
        try{
            //find all academies
            List<Academy> academies = academyService.getAll();
            //map all academies to academiesDTO
            List<AcademyDTO> academiesDTO = academies.stream().map(AcademyDTO::from).collect(Collectors.toList());
            return new ResponseEntity<>(academiesDTO, HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") int id){
        try{
            Academy academy = academyService.getById(id);
            return new ResponseEntity<>(AcademyDTO.from(academy), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> add(@RequestBody AcademyDTO academyDTO){
        try{
            Academy newAcademy = academyService.add(Academy.from(academyDTO));
            return new ResponseEntity<>(AcademyDTO.from(newAcademy), HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(ResourceAlreadyExistingException ex){
            return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") int id, @RequestBody AcademyDTO academyDTO){
        try{
            Academy updatedAcademy = academyService.updateById(id, Academy.from(academyDTO));
            return new ResponseEntity<>(AcademyDTO.from(updatedAcademy), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(ResourceAlreadyExistingException ex){
            return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
