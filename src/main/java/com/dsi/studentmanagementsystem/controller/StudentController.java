package com.dsi.studentmanagementsystem.controller;

import com.dsi.studentmanagementsystem.entity.Course;
import com.dsi.studentmanagementsystem.entity.Student;
import com.dsi.studentmanagementsystem.service.CourseService;
import com.dsi.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("courseList",studentService.findById(id).getCourseList());
        model.addAttribute("name",studentService.findNameById(id));
        return "course";
    }
    @RequestMapping("/search")
    public String searchByName(@RequestParam String name, RedirectAttributes model){
        model.addFlashAttribute("matched",studentService.findByName(name));
        return "redirect:/";
    }
}
