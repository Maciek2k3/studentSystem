package com.maciek.studentsystem.controller;

import com.maciek.studentsystem.entity.Student;
import com.maciek.studentsystem.repo.StudentRepo;
import com.maciek.studentsystem.service.StudentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@NoArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepo studentRepo;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String studentsList(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student, Model model) {
        Optional<Student> existingStudent = Optional.of(studentService.getStudentById(id).get());
        existingStudent.get().getId();
        existingStudent.get().setFirstName(student.getFirstName());
        existingStudent.get().setLastName(student.getLastName());
        existingStudent.get().setEMail(student.getEMail());

        studentService.updateStudent(existingStudent.get());
        return "redirect:/students";

    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/all")
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void saveData() {
        Student student = new Student(1L, "NoName", "NoName", "email");
        studentRepo.save(student);

    }


}
