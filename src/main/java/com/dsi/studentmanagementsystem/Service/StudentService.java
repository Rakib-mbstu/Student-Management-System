package com.dsi.studentmanagementsystem.Service;

import com.dsi.studentmanagementsystem.Dao.StudentRepository;
import com.dsi.studentmanagementsystem.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student){

        studentRepository.save(student);
    }
}
