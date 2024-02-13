package com.dsi.studentmanagementsystem.Controller;

import com.dsi.studentmanagementsystem.Entity.Course;
import com.dsi.studentmanagementsystem.Entity.Student;
import com.dsi.studentmanagementsystem.Service.CourseService;
import com.dsi.studentmanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @RequestMapping("/registerStudent")
    public String registerStudent(Student student, @RequestParam(value = "check",required = false) String[] selectdIds)
    {
        student.setCourseList(courseService.courses(selectdIds));
        studentService.save(student);
        return "home";
    }
    @RequestMapping("registerPage")
    public String registerPage(Model model)
    {
        List<Course> list = courseService.courses();
        model.addAttribute("list",list);
        return "register";
    }
}
