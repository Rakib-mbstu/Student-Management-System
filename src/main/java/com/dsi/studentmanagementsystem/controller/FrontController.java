package com.dsi.studentmanagementsystem.controller;

import com.dsi.studentmanagementsystem.entity.Student;
import com.dsi.studentmanagementsystem.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class FrontController {
    private final StudentService studentService;

    public FrontController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @RequestMapping("/")
//    public String frontPage(Model model){
//        Pageable pageable = PageRequest.of(0,10);
//        Page<Student> page = studentService.findAll(pageable);
//        List<Student> studentList = page.getContent();
//        model.addAttribute("studentList",studentList);
//        model.addAttribute("currentPage",0);
//        model.addAttribute("totalPage",page.getTotalPages());
//        model.addAttribute("totalItems",page.getTotalElements());
//        return "home";
//    }
    @RequestMapping("/")
    public String pages(@RequestParam("page") Optional<Integer> pageNumber,@RequestParam("items") Optional<Integer> items, Model model){
        Pageable pageable = PageRequest.of(pageNumber.orElse(0),items.orElse(10));
        Page<Student> page = studentService.findAll(pageable);
        List<Student> studentList = page.getContent();
        model.addAttribute("studentList",studentList);
        model.addAttribute("currentPage",pageNumber.orElse(0));
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("items",items.orElse(10));
        return "home";
    }
}
