package com.dsi.studentmanagementsystem.service;

import com.dsi.studentmanagementsystem.entity.Course;
import com.dsi.studentmanagementsystem.repository.StudentRepository;
import com.dsi.studentmanagementsystem.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public void save(Student student, String[] selectedIds){
        List<Course> courseList = courseService.courses(selectedIds);
        student.setCourseList(courseList);
        for(Course course:courseList)
        {
            List<Student> studentList = course.getStudentList();
            studentList.add(student);
        }
        studentRepository.save(student);
    }
    public void save(Student student){

        studentRepository.save(student);
    }

    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public List<Student> findAll(){
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
