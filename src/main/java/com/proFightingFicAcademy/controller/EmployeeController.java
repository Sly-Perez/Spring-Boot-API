package com.proFightingFicAcademy.controller;


import com.proFightingFicAcademy.dto.EmployeeDTO;
import com.proFightingFicAcademy.exception.ResourceAlreadyExistingException;
import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.Employee;

import com.proFightingFicAcademy.response.ResponseHandler;
import com.proFightingFicAcademy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pff_api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        try{
            List<Employee> employees = employeeService.getAll();
            List<EmployeeDTO> employeesDTO = employees.stream().map(EmployeeDTO::from).toList();
            return new ResponseEntity<>(employeesDTO, HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") int id){
        try{
            Employee employee = employeeService.getById(id);
            return new ResponseEntity<>(EmployeeDTO.from(employee), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> add(@RequestBody EmployeeDTO employeeDTO){
        try{
            Employee newEmployee = employeeService.add(Employee.from(employeeDTO));
            return new ResponseEntity<>(EmployeeDTO.from(newEmployee), HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(ResourceAlreadyExistingException ex){
            return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable("id") int id, @RequestBody EmployeeDTO employeeDTO){
        try{
            Employee updatedEmployee = employeeService.updateById(id, Employee.from(employeeDTO));
            return new ResponseEntity<>(EmployeeDTO.from(updatedEmployee), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(ResourceAlreadyExistingException ex){
            return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(int id){
        try{
            Employee deletedEmployee = employeeService.deleteById(id);
            return new ResponseEntity<>(EmployeeDTO.from(deletedEmployee), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exc){
            return ResponseHandler.responseBuilder("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
