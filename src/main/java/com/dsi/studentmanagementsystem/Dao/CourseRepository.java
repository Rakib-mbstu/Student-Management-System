package com.dsi.studentmanagementsystem.Dao;

import com.dsi.studentmanagementsystem.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByCourseId(Integer integer);
}
