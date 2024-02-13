package com.dsi.studentmanagementsystem.Service;

import com.dsi.studentmanagementsystem.Dao.CourseRepository;
import com.dsi.studentmanagementsystem.Entity.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Course> courses(){
        List<Course> list;
        list = courseRepository.findAll();
        return list;
    }
    public List<Course> courses(String[] strings)
    {
        List<Course> courseList = new ArrayList<>();
        for(String id:strings)
        {
            courseList.add(courseRepository.findByCourseId(Integer.valueOf(id)));
        }
        return courseList;
    }
}
