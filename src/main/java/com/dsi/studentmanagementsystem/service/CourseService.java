package com.dsi.studentmanagementsystem.service;

import com.dsi.studentmanagementsystem.repository.CourseRepository;
import com.dsi.studentmanagementsystem.entity.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void save(Course course){
        courseRepository.save(course);
    }
    public List<Course> findAll(){
        List<Course> list;
        list = courseRepository.findAll();
        return list;
    }
    public List<Course> courses(String[] strings)
    {
        List<Course> courseList = new ArrayList<>();
        for(String id:strings)
        {
            courseList.add(courseRepository.findByCourseId(Integer.parseInt(id)));
        }
        return courseList;
    }
    public List<Course> courses()
    {
        return courseRepository.findAll();
    }

    public Course findByCourseId(int id) {
        return courseRepository.findByCourseId(id);
    }

    public void deleteById(int id) {
        courseRepository.deleteById((long) id);
    }
}
