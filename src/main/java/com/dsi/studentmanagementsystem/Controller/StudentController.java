package com.dsi.studentmanagementsystem.Controller;

import com.dsi.studentmanagementsystem.Entity.Course;
import com.dsi.studentmanagementsystem.Entity.Student;
import com.dsi.studentmanagementsystem.Service.CourseService;
import com.dsi.studentmanagementsystem.Service.StudentService;
import org.hibernate.boot.archive.scan.internal.ScanResultImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String registerStudent(Student student, @RequestParam(value = "selectedIds",required = false) String[] selectedIds)
    {
        System.out.println(selectedIds.length);
        List<Course> courseList = courseService.courses(selectedIds);
        student.setCourseList(courseList);
        for(Course course:courseList)
        {
            List<Student> studentList = course.getStudentList();
            studentList.add(student);
        }
        studentService.save(student);
        return "redirect:/";
    }
    @RequestMapping("registerPage")
    public String register(Model model)
    {
        List<Course> list = courseService.courses();
        model.addAttribute("list",list);
        return "register";
    }
    @RequestMapping("/updatePage/{id}")
    public String update(@PathVariable("id") int id,Model model)
    {
        model.addAttribute("student",studentService.findById(id));
        return "update";
    }
    @RequestMapping("/updateStudent")
    public String updateStudent(Student student)
    {
        System.out.println(student.toString());
        studentService.save(student);
        return "redirect:/";
    }
    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id)
    {
        studentService.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/showCourses/{id}")
    public String showCourses(@PathVariable("id") int id, Model model)
    {
        System.out.println("okay");
        model.addAttribute("courseList",studentService.findById(id).getCourseList());
        return "course";
    }
}
