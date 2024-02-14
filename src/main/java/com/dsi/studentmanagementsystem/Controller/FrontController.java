package com.dsi.studentmanagementsystem.Controller;

import com.dsi.studentmanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
