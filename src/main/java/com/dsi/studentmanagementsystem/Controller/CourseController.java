package com.dsi.studentmanagementsystem.Controller;

import com.dsi.studentmanagementsystem.Dao.CourseRepository;
import com.dsi.studentmanagementsystem.Entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public String saveCourses(Course course){
        courseRepository.save(course);
        return "redirect:/";
    }
    @RequestMapping("/viewCourses")
    public String viewCourses(Model model){
        model.addAttribute("courseList",courseRepository.findAll());
        return "viewCourses";
    }
    @RequestMapping(value = "/coursePage")
    public String addCoursePage()
    {
        return "addCourse";
    }
    @RequestMapping("/updateCoursePage/{id}")
    public String updateCoursePage(@PathVariable("id") int id,Model model){
        model.addAttribute("course",courseRepository.findByCourseId(id));
        return "updateCourse";
    }
    @RequestMapping(value = "/updateCourse",method = RequestMethod.POST)
    public String updateCoursePage(Course course){
        System.out.println(course.getCourseName());
        courseRepository.save(course);
        return "redirect:/viewCourses";
    }
    @RequestMapping("/deleteCourse/{id}")
    public String delete(@PathVariable("id") int id)
    {
        courseRepository.deleteById((long) id);
        return "redirect:/viewCourses";
    }
}
