package com.proFightingFicAcademy.serviceImp;

import com.proFightingFicAcademy.exception.ResourceAlreadyExistingException;
import com.proFightingFicAcademy.exception.ResourceNotFoundException;
import com.proFightingFicAcademy.model.Academy;
import com.proFightingFicAcademy.model.State;
import com.proFightingFicAcademy.model.Student;
import com.proFightingFicAcademy.repository.StudentRepository;


import com.proFightingFicAcademy.service.AcademyService;
import com.proFightingFicAcademy.service.StateService;
import com.proFightingFicAcademy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StateService stateService;

    @Autowired
    private AcademyService academyService;

    @Override
    public List<Student> getAll() {
        State state = stateService.getById(1);
        return studentRepository.findAllByState(state);
    }

    @Override
    public List<Student> getAllByAcademy(Academy academy){
        State state = stateService.getById(1);
        return studentRepository.findAllByAcademyAndState(academy, state);
    }

    @Override
    public Student getById(int id) {
        State state = stateService.getById(1);
        return studentRepository.findByIdAndState(id, state).orElseThrow(()->new ResourceNotFoundException("Student with id " + id + " was not found"));
    }

    @Override
    public Student add(Student student) {
        if(student.getId() != 0){
            throw new ResourceAlreadyExistingException("Cannot add a student with id assigned beforehand");
        }
        student.setId(null);

        State activeState = stateService.getById(1);
        Academy academy = academyService.getById(student.getAcademy().getId());

        Student foundedStudent = studentRepository.findByEmailAndState(student.getEmail(), activeState);

        if(foundedStudent != null){
            throw new ResourceAlreadyExistingException("Student with email " + student.getEmail() + " already exists");
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);


        student.setCreatedAt(formattedNow);
        student.setUpdatedAt(null);
        student.setState(activeState);
        student.setAcademy(academy);

        studentRepository.save(student);

        return student;
    }

    @Override
    public Student updateById(int id, Student student){
        if(student.getId() != 0){
            throw new ResourceAlreadyExistingException("Cannot update a student with id attached to the body; Just indicate the id through the path");
        }

        Student updateStudent = this.getById(id);

        State activeState = stateService.getById(1);
        Academy academy = academyService.getById(student.getAcademy().getId());


        Student foundedStudent = studentRepository.findByEmailAndState(student.getEmail(), activeState);

        if( (foundedStudent != null)  &&  (foundedStudent.getId() != updateStudent.getId()) ){
            throw new ResourceAlreadyExistingException("Student with email " + student.getEmail() + " already exists");
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);


        updateStudent.setFullname(student.getFullname());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setCountry(student.getCountry());
        updateStudent.setUpdatedAt(formattedNow);
        updateStudent.setAcademy(academy);

        studentRepository.save(updateStudent);

        return updateStudent;
    }

    @Override
    public Student deleteById(int id) {
        Student deletedStudent = this.getById(id);

        State inactiveState = stateService.getById(2);
        deletedStudent.setState(inactiveState);

        studentRepository.save(deletedStudent);

        return deletedStudent;
    }

}
