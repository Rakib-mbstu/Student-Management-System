package com.dsi.studentmanagementsystem.Controller;

import com.dsi.studentmanagementsystem.Dao.CourseRepository;
import com.dsi.studentmanagementsystem.Entity.Course;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class CourseController {
    private CourseRepository courseRepository;
    @RequestMapping("/saveCourses")
    public String saveCourses(Course course){
        courseRepository.save(course);
        return "home";
    }
}
