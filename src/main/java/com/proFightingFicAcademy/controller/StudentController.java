package com.proFightingFicAcademy.controller;


import com.proFightingFicAcademy.dto.StudentDTO;
import com.proFightingFicAcademy.exception.ResourceAlreadyExistingException;
import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.Student;

import com.proFightingFicAcademy.response.ResponseHandler;
import com.proFightingFicAcademy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/pff_api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        try{
            List<Student> students = studentService.getAll();
            List<StudentDTO> studentsDTO = students.stream().map(StudentDTO::from).collect(Collectors.toList());
            return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") int id){
        try{
            Student student = studentService.getById(id);
            return new ResponseEntity<>(StudentDTO.from(student), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> add(@RequestBody StudentDTO studentDTO){
        try{
            Student newStudent = studentService.add(Student.from(studentDTO));
            return new ResponseEntity<>(StudentDTO.from(newStudent), HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(ResourceAlreadyExistingException ex){
            return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO){
        try{
            Student updatedStudent = studentService.updateById(id, Student.from(studentDTO));
            return new ResponseEntity<>(StudentDTO.from(updatedStudent), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(ResourceAlreadyExistingException ex){
            return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") int id){
        try{
            Student deletedStudent = studentService.deleteById(id);
            return new ResponseEntity<>(StudentDTO.from(deletedStudent), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
