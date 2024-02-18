package com.dsi.studentmanagementsystem.service;

import com.dsi.studentmanagementsystem.repository.StudentRepository;
import com.dsi.studentmanagementsystem.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student){

        studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    public void deleteById(int id) {
        studentRepository.deleteById((long) id);
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByFirstNameOrLastName(name);
    }

    public String findNameById(int id) {
        Student student = studentRepository.findById(id);
        return student.getFirstName() + " "+ student.getLastName();
    }
}
