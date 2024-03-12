package com.dsi.studentmanagementsystem.controller;

import com.dsi.studentmanagementsystem.repository.CourseRepository;
import com.dsi.studentmanagementsystem.entity.Course;
import com.dsi.studentmanagementsystem.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {
    private final CourseService courseService;

    public CourseController( CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public String saveCourses(Course course){
        courseService.save(course);
        return "redirect:/viewCourses";
    }
    @RequestMapping("/viewCourses")
    public String viewCourses(Model model){
        model.addAttribute("courseList",courseService.findAll());
        return "viewCourses";
    }
    @RequestMapping(value = "/coursePage")
    public String addCoursePage()
    {
        return "addCourse";
    }
    @RequestMapping("/updateCoursePage/{id}")
    public String updateCoursePage(@PathVariable("id") int id,Model model){
        model.addAttribute("course",courseService.findByCourseId(id));
        return "updateCourse";
    }
    @RequestMapping(value = "/updateCourse",method = RequestMethod.POST)
    public String updateCoursePage(Course course){

        courseService.save(course);
        return "redirect:/viewCourses";
    }
    @RequestMapping("/deleteCourse/{id}")
    public String delete(@PathVariable("id") int id)
    {
        courseService.deleteById(id);
        return "redirect:/viewCourses";
    }
}
