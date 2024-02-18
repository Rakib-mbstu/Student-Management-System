package com.dsi.studentmanagementsystem.repository;

import com.dsi.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long>{
    Student findById(int id);

   @Query(value = "select * from student where (first_name=:name or last_name=:name)",nativeQuery = true)
    List<Student> findByFirstNameOrLastName(String name);
}
