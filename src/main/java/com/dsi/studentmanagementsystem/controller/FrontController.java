package com.dsi.studentmanagementsystem.controller;

import com.dsi.studentmanagementsystem.entity.Student;
import com.dsi.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FrontController {
    private final StudentService studentService;

    public FrontController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/")
    public String frontPage(Model model)
    {
        model.addAttribute("studentList",studentService.findAll());
        return "home";
    }
}
