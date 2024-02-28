package com.dsi.studentmanagementsystem.controller;

import com.dsi.studentmanagementsystem.entity.Course;
import com.dsi.studentmanagementsystem.entity.Student;
import com.dsi.studentmanagementsystem.service.CourseService;
import com.dsi.studentmanagementsystem.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }
    @RequestMapping("/")
    public String pages(@RequestParam("page") Optional<Integer> pageNumber,
                        @RequestParam("item") Optional<Integer> items,
                        @RequestParam("previousItems") Optional<Integer> previousItems,
                        Model model) {
        Logger logger = LoggerFactory.getLogger(FrontController.class);
        int page = pageNumber.orElse(0);
        boolean changePage = false;
        int newPageNumber = 0;
        if(previousItems.isPresent())
        {
            int prev = previousItems.orElse(10);
            int t_item = items.orElse(10);
            page = (page*prev)/t_item;
            logger.info(String.valueOf(page));
            pageNumber = Optional.of(page);
            newPageNumber = page;
            logger.info("page : "+String.valueOf(pageNumber));
            changePage = true;
        }
        Pageable pageable = PageRequest.of(page, items.orElse(10));
        Page<Student> pages = studentService.findAll(pageable);
        List<Student> studentList = pages.getContent();
        model.addAttribute("studentList", studentList);
        model.addAttribute("currentPage", pageNumber.orElse(0));
        model.addAttribute("totalPage", pages.getTotalPages());
        model.addAttribute("items", items.orElse(10));


        return "home";
    }

    @RequestMapping("/registerStudent")
    public String registerStudent(Student student, @RequestParam(value = "selectedIds",required = false) String[] selectedIds)
    {
        studentService.save(student,selectedIds);
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
