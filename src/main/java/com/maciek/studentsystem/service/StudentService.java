package com.maciek.studentsystem.service;

import com.maciek.studentsystem.entity.Student;
import com.maciek.studentsystem.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();

    }

    public Student saveStudent(Student student) {
        return studentRepo.save(student);

    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    public Student updateStudent(Student student) {
        return studentRepo.save(student);

    }
    public void deleteStudent(Long id){
        studentRepo.deleteById(id);

    }
}