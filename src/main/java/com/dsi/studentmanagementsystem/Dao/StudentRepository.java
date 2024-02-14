package com.dsi.studentmanagementsystem.Dao;

import com.dsi.studentmanagementsystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long>{
    Student findById(int id);
}
