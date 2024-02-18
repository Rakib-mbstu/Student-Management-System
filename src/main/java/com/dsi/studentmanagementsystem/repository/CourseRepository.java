package com.dsi.studentmanagementsystem.repository;

import com.dsi.studentmanagementsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Course findByCourseId(int id);
}
